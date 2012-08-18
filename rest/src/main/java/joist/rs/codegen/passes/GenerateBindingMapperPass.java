package joist.rs.codegen.passes;

import joist.codegen.dtos.Entity;
import joist.codegen.dtos.ManyToManyProperty;
import joist.codegen.dtos.ManyToOneProperty;
import joist.codegen.dtos.OneToManyProperty;
import joist.codegen.dtos.PrimitiveProperty;
import joist.codegen.passes.Pass;
import joist.rs.Link;
import joist.rs.LinkCollection;
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
    this.addCopyPrimitiveProperties(to, restEntity);
    this.addCopyManyToOneProperties(to, restEntity);
    this.addCopyOneToManyProperties(to, restEntity);
    this.addCopyManyToManyProperties(to, restEntity);
    to.body.line("return binding;");

    bindingMapper.addImports(Link.class);
    bindingMapper.addImports(LinkCollection.class);
    bindingMapper.addImports(restEntity.entity.getFullClassName());
  }

  private void addCopyPrimitiveProperties(GMethod to, RestEntity restEntity) {
    for (PrimitiveProperty p : restEntity.entity.getPrimitiveProperties()) {
      to.body.line("binding.{} = domainObject.get{}();", p.getVariableName(), p.getCapitalVariableName());
    }
  }

  private void addCopyManyToOneProperties(GMethod to, RestEntity restEntity) {
    for (ManyToOneProperty p : restEntity.entity.getManyToOneProperties()) {
      String domainGetter = "domainObject.get" + p.getCapitalVariableName() + "()";
      if (p.getOneSide().isCodeEntity()) {
        to.body.line("binding.{} = {} == null ? null : {}.toString();", p.getVariableName(), domainGetter, domainGetter);
      } else {
        to.body.line("binding.{} = {} == null ? null : new Link({});", p.getVariableName(), domainGetter, domainGetter);
      }
    }
  }

  private void addCopyOneToManyProperties(GMethod to, RestEntity restEntity) {
    for (OneToManyProperty p : restEntity.entity.getOneToManyProperties()) {
      if (p.isCollectionSkipped() || p.isManyToMany()) {
        continue;
      }
      if (p.isOneToOne()) {
        String domainGetter = "domainObject.get" + p.getCapitalVariableNameSingular() + "()";
        to.body.line("binding.{} = {} == null ? null : new Link({});", p.getVariableName(), domainGetter, domainGetter);
      } else {
        String domainGetter = "domainObject.get" + p.getCapitalVariableName() + "()";
        to.body.line("binding.{} = {} == null ? null : new LinkCollection(0, {});", p.getVariableName(), domainGetter, domainGetter);
      }
    }
  }

  private void addCopyManyToManyProperties(GMethod to, RestEntity restEntity) {
    for (ManyToManyProperty p : restEntity.entity.getManyToManyProperties()) {
      if (p.getMySideOneToMany().isCollectionSkipped()) {
        continue;
      }
      String domainGetter = "domainObject.get" + p.getCapitalVariableName() + "()";
      to.body.line("binding.{} = {} == null ? null :new LinkCollection(0, {});", p.getVariableName(), domainGetter, domainGetter);
    }
  }
}
