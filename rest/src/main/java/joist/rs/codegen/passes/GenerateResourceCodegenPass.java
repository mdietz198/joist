package joist.rs.codegen.passes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import joist.codegen.dtos.Entity;
import joist.codegen.passes.Pass;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.codegen.entities.RestEntity;
import joist.sourcegen.GClass;
import joist.sourcegen.GField;
import joist.sourcegen.GMethod;

public class GenerateResourceCodegenPass implements Pass {

  public void pass(joist.codegen.Codegen c) {
    // TODO Nasty hack to get my subclassed codegen
    joist.rs.codegen.Codegen codegen = (joist.rs.codegen.Codegen) c;
    for (Entity entity : codegen.getEntities().values()) {
      if (entity.isCodeEntity() || entity.isAbstract()) {
        continue;
      }

      RestEntity restEntity = new RestEntity(entity);
      GClass resourceCodegen = codegen.getOutputCodegenDirectory().getClass(restEntity.getFullResourceClassName());
      resourceCodegen.addImports(entity.getFullClassName());
      // TODO do I need a base class?
      // resourceCodegen.baseClass(???)

      this.annotations(resourceCodegen, restEntity);
      this.addRepository(resourceCodegen);
      this.addGet(resourceCodegen, restEntity);
      this.addPut(resourceCodegen, restEntity);
      this.addDelete(resourceCodegen, restEntity);
    }
  }

  private void annotations(GClass resourceCodegen, RestEntity restEntity) {
    resourceCodegen.addAnnotation("@Path(\"/" + restEntity.entity.getVariableName() + "s/{id}\")");
    resourceCodegen.addImports(Path.class);
  }

  private void addRepository(GClass resourceCodegen) {
    GField repoRef = resourceCodegen.getField("repository").setProtected();
    repoRef.type(Repository.class);
    // TODO add annotation so I can inject Repository
    // See http://stackoverflow.com/questions/7985231/injecting-into-a-jersey-resource-class
    // repoRef.addAnnotation(annotation, args)
  }

  private void addGet(GClass resourceCodegen, RestEntity restEntity) {
    GMethod get = resourceCodegen.getMethod("get");
    // TODO add application/json
    get.addAnnotation("@GET").addAnnotation("@Produces({ \"application/xml\" })");
    get.argument("final @PathParam(\"id\") Long", "id");
    get.returnType(restEntity.getBindingClassName());
    get.body.line("return UoW.read(Registry.getRepository(), new BlockWithReturn<{}>() {", restEntity.getBindingClassName());
    get.body.line("_   public {} go() {", restEntity.getBindingClassName());
    get.body.line("_   _   return BindingMapper.toBinding({}.queries.find(id));", restEntity.entity.getClassName());
    get.body.line("_   }");
    get.body.line("});");
    resourceCodegen.addImports(GET.class, Produces.class, PathParam.class, UoW.class, BlockWithReturn.class);
    // TODO replace with injected repository reference
    resourceCodegen.addImports(restEntity.getFullBindingClassName(), "features.Registry", restEntity.getRsConfig().getRestHelpersPackage()
      + ".BindingMapper");
  }

  private void addPut(GClass resourceCodegen, RestEntity restEntity) {
    // TODO Auto-generated method stub

  }

  private void addDelete(GClass resourceCodegen, RestEntity restEntity) {
    // TODO Auto-generated method stub

  }

}