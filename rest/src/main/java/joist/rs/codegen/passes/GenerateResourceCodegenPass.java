package joist.rs.codegen.passes;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import joist.codegen.dtos.Entity;
import joist.codegen.passes.Pass;
import joist.domain.exceptions.NotFoundException;
import joist.domain.orm.Repository;
import joist.domain.uow.Block;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.AbstractResource;
import joist.rs.codegen.RestCodegen;
import joist.rs.codegen.entities.RestEntity;
import joist.sourcegen.GClass;
import joist.sourcegen.GMethod;

public class GenerateResourceCodegenPass implements Pass<RestCodegen> {

  public void pass(RestCodegen codegen) {
    for (Entity entity : codegen.getSchema().getEntities().values()) {
      if (entity.isCodeEntity() || entity.isAbstract()) {
        continue;
      }

      RestEntity restEntity = new RestEntity(entity, codegen.getConfig());
      GClass resourceCodegen = codegen.getOutputCodegenDirectory().getClass(restEntity.getFullResourceClassName());
      resourceCodegen.addImports(entity.getFullClassName());
      resourceCodegen.addImports(Context.class, Repository.class, AbstractResource.class);
      resourceCodegen.baseClassName("AbstractResource<{}>", restEntity.getBindingClassName());

      this.annotations(resourceCodegen, restEntity);
      this.addGet(resourceCodegen, restEntity);
      this.addPut(resourceCodegen, restEntity);
      this.addDelete(resourceCodegen, restEntity);
    }
  }

  private void annotations(GClass resourceCodegen, RestEntity restEntity) {
    resourceCodegen.addAnnotation("@Path(\"/" + restEntity.entity.getVariableName() + "s/{id}\")");
    resourceCodegen.addImports(Path.class);
  }

  private void addGet(GClass resourceCodegen, RestEntity restEntity) {
    GMethod get = resourceCodegen.getMethod("get");
    get.addAnnotation("@GET").addAnnotation("@Produces({ \"application/json\", \"application/xml\" })");
    get.argument("final @Context Repository", "repo");
    get.argument("final @PathParam(\"id\") Long", "id");
    get.returnType(restEntity.getBindingClassName());
    get.body.line("return UoW.read(repo, new BlockWithReturn<{}>() {", restEntity.getBindingClassName());
    get.body.line("_   public {} go() {", restEntity.getBindingClassName());
    get.body.line("_   _   return BindingMapper.toBinding({}.queries.find(id));", restEntity.entity.getClassName());
    get.body.line("_   }");
    get.body.line("});");
    resourceCodegen.addImports(GET.class, Produces.class, PathParam.class, UoW.class, BlockWithReturn.class);
    resourceCodegen.addImports(restEntity.getFullBindingClassName(), restEntity.getConfig().getRestHelpersPackage() + ".BindingMapper");
  }

  private void addPut(GClass resourceCodegen, RestEntity restEntity) {
    final String varName = restEntity.entity.getVariableName();
    final String className = restEntity.entity.getClassName();
    GMethod put = resourceCodegen.getMethod("put");
    put.addAnnotation("@PUT");
    put.addAnnotation("@Consumes({ \"application/json\", \"application/xml\" })");
    put.argument("final @Context Repository", "repo");
    put.argument("final @PathParam(\"id\") Long", "id");
    put.argument("final " + restEntity.getBindingClassName(), varName);
    put.body.line("UoW.go(repo, null, new Block() {");
    put.body.line("_   public void go() {");
    put.body.line("_   _   BindingMapper.toDomain(" + varName + ", " + className + ".queries.find(id));");
    put.body.line("_   }");
    put.body.line("});");
    resourceCodegen.addImports(PUT.class, Consumes.class, PathParam.class, Block.class);
    resourceCodegen.addImports(restEntity.entity.getFullClassName());
  }

  private void addDelete(GClass resourceCodegen, RestEntity restEntity) {
    final String className = restEntity.entity.getClassName();
    GMethod delete = resourceCodegen.getMethod("delete");
    delete.addAnnotation("@DELETE");
    delete.argument("final @Context Repository", "repo");
    delete.argument("final @PathParam(\"id\") Long", "id");
    delete.body.line("UoW.go(repo, null, new Block() {");
    delete.body.line("_   public void go() {");
    delete.body.line("_   _   try {");
    delete.body.line("_   _   _   " + className + ".queries.delete(" + className + ".queries.find(id));");
    delete.body.line("_   _   } catch (NotFoundException e) {");
    delete.body.line("_   _   _   // Ignore to make DELETE idempotentA");
    delete.body.line("_   _   }");
    delete.body.line("_   }");
    delete.body.line("});");
    resourceCodegen.addImports(DELETE.class, PathParam.class, Block.class, NotFoundException.class);
    resourceCodegen.addImports(restEntity.entity.getFullClassName());
  }

}
