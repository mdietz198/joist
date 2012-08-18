package joist.rs.codegen.passes;

import joist.codegen.dtos.Entity;
import joist.codegen.passes.Pass;
import joist.rs.codegen.entities.RestEntity;
import joist.sourcegen.Argument;
import joist.sourcegen.GClass;
import joist.sourcegen.GMethod;

public class GenerateBindingMapperPass implements Pass {

  public void pass(joist.codegen.Codegen c) {
    // TODO Nasty hack to get my subclassed codegen
    joist.rs.codegen.Codegen codegen = (joist.rs.codegen.Codegen) c;

    GClass bindingMapper = codegen.getOutputCodegenDirectory().getClass(codegen.getRsConfig().getRestHelpersPackage() + ".BindingMapper");
    bindingMapper.getConstructor().setPrivate();

    for (Entity entity : codegen.getEntities().values()) {
      if (entity.isCodeEntity() || entity.isAbstract()) {
        continue;
      }
      RestEntity restEntity = new RestEntity(entity);

      this.addToBinding(bindingMapper, restEntity);
    }
  }

  private void addToBinding(GClass bindingMapper, RestEntity restEntity) {
    Argument arg = new Argument(restEntity.entity.getClassName(), "domainObject");
    GMethod to = bindingMapper.getMethod("toBinding", arg);
    to.setStatic();
    to.returnType(restEntity.getFullBindingClassName());

    to.body.line("{} binding = new {}();", restEntity.getBindingClassName(), restEntity.getBindingClassName());

    to.body.line("return binding;");

    bindingMapper.addImports(restEntity.entity.getFullClassName());
  }
}
