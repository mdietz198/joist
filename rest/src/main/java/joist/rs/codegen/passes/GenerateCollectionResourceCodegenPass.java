package joist.rs.codegen.passes;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

import joist.codegen.dtos.Entity;
import joist.codegen.dtos.ManyToOneProperty;
import joist.codegen.dtos.PrimitiveProperty;
import joist.codegen.passes.Pass;
import joist.domain.orm.Repository;
import joist.domain.orm.queries.Select;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.CollectionLinkBinding;
import joist.rs.PagedCollectionBinding;
import joist.rs.codegen.RestCodegen;
import joist.rs.codegen.entities.RestEntity;
import joist.sourcegen.GClass;
import joist.sourcegen.GMethod;

public class GenerateCollectionResourceCodegenPass implements Pass<RestCodegen> {

  public void pass(RestCodegen codegen) {
    for (Entity entity : codegen.getSchema().getEntities().values()) {
      if (entity.isCodeEntity() || entity.isAbstract()) {
        continue;
      }

      RestEntity restEntity = new RestEntity(entity, codegen.getConfig());
      GClass resourceCodegen = codegen.getOutputCodegenDirectory().getClass(restEntity.getFullResourceCollectionClassName());
      resourceCodegen.addImports(entity.getFullClassName());
      resourceCodegen.addImports(Context.class, Repository.class);
      // TODO do I need a base class?
      // resourceCodegen.baseClass(???)

      this.annotations(resourceCodegen, restEntity);
      this.addGet(resourceCodegen, restEntity);
      this.addPost(resourceCodegen, restEntity);
    }
  }

  private void annotations(GClass resourceCodegen, RestEntity restEntity) {
    resourceCodegen.addAnnotation("@Path(\"/" + restEntity.entity.getVariableName() + "s\")");
    resourceCodegen.addImports(Path.class);
  }

  private void addGet(GClass resourceCodegen, RestEntity restEntity) {
    GMethod get = resourceCodegen.getMethod("get");
    get.addAnnotation("@GET").addAnnotation("@Produces({ \"application/json\", \"application/xml\" })");
    get.argument("final @Context Repository", "repo");
    get.argument("final @QueryParam(\"startIndex\") Integer", "startIndexParam");
    get.argument("final @QueryParam(\"maxResults\") Integer", "maxResultsParam");
    resourceCodegen.addImports(QueryParam.class);
    for (PrimitiveProperty p : restEntity.getPrimitivePropertiesIncludingInherited()) {
      if (!this.skipQueryParamForProperty(p)) {
        get.argument("final @QueryParam(\"" + p.getVariableName() + "\") " + p.getJavaType(), p.getVariableName());
      }
    }
    for (ManyToOneProperty p : restEntity.getManyToOnePropertiesIncludingInherited()) {
      Class<?> paramType = p.getOneSide().isCodeEntity() ? String.class : Long.class;
      get.argument("final @QueryParam(\"" + p.getVariableName() + "\") " + paramType.getSimpleName(), p.getVariableName());
      resourceCodegen.addImports(QueryParam.class);
    }
    get.returnType(PagedCollectionBinding.class);
    get.body.line("return UoW.read(repo, new BlockWithReturn<PagedCollectionBinding>() {");
    get.body.line("_   public PagedCollectionBinding go() {");
    get.body.line("_   _   Integer startIndex = startIndexParam == null ? 0 : startIndexParam;");
    get.body.line("_   _   Integer maxResults = maxResultsParam == null ? {} : maxResultsParam;", restEntity.getConfig().defaultMaxResults);
    get.body.line("_   _   {} {} = new {}();", restEntity.entity.getAliasName(), restEntity.entity.getAliasAlias(), restEntity.entity.getAliasName());
    get.body.line("_   _   Select<{}> q = Select.from({});", restEntity.entity.getClassName(), restEntity.entity.getAliasAlias());
    for (PrimitiveProperty p : restEntity.getPrimitivePropertiesIncludingInherited()) {
      if (!this.skipQueryParamForProperty(p)) {
        get.body.line("_   _   if({} != null) {", p.getVariableName());
        get.body.line("_   _   _   q.where({}.{}.eq({}));", restEntity.entity.getAliasAlias(), p.getVariableName(), p.getVariableName());
        get.body.line("_   _   }");
      }
    }
    for (ManyToOneProperty p : restEntity.getManyToOnePropertiesIncludingInherited()) {
      get.body.line("_   _   if({} != null) {", p.getVariableName());
      String eqExpr;
      Entity propEntity = p.getOneSide();
      if (propEntity.isCodeEntity()) {
        eqExpr = propEntity.getClassName() + ".fromCode(" + p.getVariableName() + ")";
        resourceCodegen.addImports(propEntity.getFullClassName());
      } else {
        eqExpr = p.getVariableName();
      }
      get.body.line("_   _   _   q.where({}.{}.eq({}));", restEntity.entity.getAliasAlias(), p.getVariableName(), eqExpr);
      get.body.line("_   _   }");
    }
    get.body.line("_   _   q.orderBy({}.id.asc());", restEntity.entity.getAliasAlias());
    get.body.line("_   _   q.offset(startIndex);");
    get.body.line("_   _   q.limit(maxResults );");
    get.body.line("_   _   List<{}> list = q.list();", restEntity.entity.getClassName());
    get.body.line("_   _   PagedCollectionBinding result = new PagedCollectionBinding();");
    get.body.line("_   _   result.setLinksFromDomainObjects(list);");
    get.body.line("_   _   if (startIndex > 0) {");
    get.body.line(
      "_   _   _   result.setPrevious(new CollectionLinkBinding({}.class, Math.max(0, startIndex - maxResults), Math.min(startIndex, maxResults)));",
      restEntity.entity.getClassName());
    get.body.line("_   _   }");
    get.body.line("_   _   if (!list.isEmpty() && list.size() == maxResults) {");
    get.body.line(
      "_   _   _   result.setNext(new CollectionLinkBinding({}.class, startIndex + maxResults, maxResults));",
      restEntity.entity.getClassName());
    get.body.line("_   _   }");
    get.body.line("_   _   return result;");
    get.body.line("_   }");
    get.body.line("});");
    resourceCodegen.addImports(
      GET.class,
      Produces.class,
      PagedCollectionBinding.class,
      UoW.class,
      BlockWithReturn.class,
      Select.class,
      List.class,
      CollectionLinkBinding.class,
      Math.class);
    resourceCodegen.addImports(restEntity.entity.getFullAliasClassName());
  }

  private boolean skipQueryParamForProperty(PrimitiveProperty p) {
    return p.getVariableName().equals("id")
      || p.getVariableName().equals("version")
      || p.getJavaType().equals("com.domainlanguage.time.TimePoint")
      || p.getJavaType().equals("com.domainlanguage.time.CalendarDate")
      || p.getJavaType().equals("com.domainlanguage.money.Money");
    // TODO add providers for Joist non-standard primitives
  }

  private void addPost(GClass resourceCodegen, RestEntity restEntity) {
    final String varName = restEntity.entity.getVariableName();
    final String className = restEntity.entity.getClassName();
    GMethod post = resourceCodegen.getMethod("post");
    post.returnType(Long.class);
    post.addAnnotation("@POST").addAnnotation("@Consumes({ \"application/json\", \"application/xml\" })");
    post.argument("final @Context Repository", "repo");
    post.argument("final " + restEntity.getBindingClassName(), varName);
    post.body.line("return UoW.go(repo, null, new BlockWithReturn<" + className + ">() {");
    post.body.line("_   public " + className + " go() {");
    post.body.line("_   _   " + className + " domainObject = new " + className + "();");
    post.body.line("_   _   BindingMapper.toDomain(" + varName + ", domainObject);");
    post.body.line("_   _   return domainObject;");
    post.body.line("_   }");
    post.body.line("}).getId();");
    // TODO make POST return the URL of the newly created object in the response header
    resourceCodegen.addImports(POST.class, Consumes.class);
    resourceCodegen.addImports(restEntity.getFullBindingClassName(), restEntity.getConfig().getRestHelpersPackage() + ".BindingMapper");
  }
}
