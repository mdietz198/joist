package joist.rs.codegen.passes;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import joist.codegen.dtos.Entity;
import joist.codegen.passes.Pass;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.LinkCollection;
import joist.rs.codegen.entities.RestEntity;
import joist.sourcegen.GClass;
import joist.sourcegen.GMethod;

public class GenerateCollectionResourceCodegenPass implements Pass {

  public void pass(joist.codegen.Codegen c) {
    // TODO Nasty hack to get my subclassed codegen
    joist.rs.codegen.Codegen codegen = (joist.rs.codegen.Codegen) c;
    for (Entity entity : codegen.getEntities().values()) {
      if (entity.isCodeEntity() || entity.isAbstract()) {
        continue;
      }

      RestEntity restEntity = new RestEntity(entity);
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
    // TODO add application/json
    get.addAnnotation("@GET").addAnnotation("@Produces({ \"application/xml\" })");
    get.argument("final @Context Repository", "repo");
    get.returnType(LinkCollection.class);
    get.body.line("return UoW.read(repo, new BlockWithReturn<LinkCollection>() {");
    get.body.line("_   public LinkCollection go() {");
    get.body.line(
      "_   _   return new LinkCollection(0, {}.class, {}.queries.findAllIds());",
      restEntity.entity.getClassName(),
      restEntity.entity.getClassName());
    get.body.line("_   }");
    get.body.line("});");
    resourceCodegen.addImports(GET.class, Produces.class, LinkCollection.class, UoW.class, BlockWithReturn.class);
  }

  private void addPost(GClass resourceCodegen, RestEntity restEntity) {
    final String varName = restEntity.entity.getVariableName();
    final String className = restEntity.entity.getClassName();
    GMethod post = resourceCodegen.getMethod("post");
    post.returnType(Long.class);
    // TODO add application/json
    post.addAnnotation("@POST").addAnnotation("@Consumes({ \"application/xml\" })");
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
    resourceCodegen.addImports(restEntity.getFullBindingClassName(), restEntity.getRsConfig().getRestHelpersPackage() + ".BindingMapper");
  }
}
