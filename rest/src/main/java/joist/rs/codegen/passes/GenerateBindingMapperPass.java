package joist.rs.codegen.passes;

import java.util.ArrayList;
import java.util.List;

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
      if (entity.isAbstract() || entity.isCodeEntity()) {
        continue;
      }
      RestEntity restEntity = new RestEntity(entity);

      this.addToBinding(bindingMapper, restEntity);
      this.addToDomain(bindingMapper, restEntity);
    }
  }

  private void addToBinding(GClass bindingMapper, RestEntity restEntity) {
    Argument arg = new Argument(restEntity.entity.getClassName(), "domainObject");
    GMethod to = bindingMapper.getMethod("toBinding", arg);
    to.setStatic();
    to.returnType(restEntity.getFullBindingClassName());

    to.body.line("{} binding = new {}();", restEntity.getBindingClassName(), restEntity.getBindingClassName());
    this.copyPrimitivePropertiesToBinding(to, restEntity);
    this.copyManyToOnePropertiesToBinding(to, restEntity);
    this.copyOneToManyPropertiesToBinding(to, restEntity);
    this.copyManyToManyPropertiesToBinding(to, restEntity);
    to.body.line("return binding;");

    bindingMapper.addImports(Link.class, LinkCollection.class);
    bindingMapper.addImports(restEntity.entity.getFullClassName(), restEntity.getFullBindingClassName());
  }

  private void copyPrimitivePropertiesToBinding(GMethod to, RestEntity restEntity) {
    for (PrimitiveProperty p : restEntity.getPrimitivePropertiesIncludingInherited()) {
      to.body.line("binding.{} = domainObject.get{}();", p.getVariableName(), p.getCapitalVariableName());
    }
  }

  private void copyManyToOnePropertiesToBinding(GMethod to, RestEntity restEntity) {
    for (ManyToOneProperty p : restEntity.getManyToOnePropertiesIncludingInherited()) {
      String domainGetter = "domainObject.get" + p.getCapitalVariableName() + "()";
      if (p.getOneSide().isCodeEntity()) {
        to.body.line("binding.{} = {} == null ? null : {}.toString();", p.getVariableName(), domainGetter, domainGetter);
      } else {
        to.body.line("binding.{} = {} == null ? null : new Link({});", p.getVariableName(), domainGetter, domainGetter);
      }
    }
  }

  private void copyOneToManyPropertiesToBinding(GMethod to, RestEntity restEntity) {
    for (OneToManyProperty p : restEntity.getOneToManyPropertiesIncludingInherited()) {
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

  private void copyManyToManyPropertiesToBinding(GMethod to, RestEntity restEntity) {
    for (ManyToManyProperty p : restEntity.getManyToManyPropertiesIncludingInherited()) {
      if (p.getMySideOneToMany().isCollectionSkipped()) {
        continue;
      }
      String domainGetter = "domainObject.get" + p.getCapitalVariableName() + "()";
      to.body.line("binding.{} = {} == null ? null : new LinkCollection(0, {});", p.getVariableName(), domainGetter, domainGetter);
    }
  }

  private void addToDomain(GClass bindingMapper, RestEntity restEntity) {
    Argument arg1 = new Argument(restEntity.getBindingClassName(), "binding");
    Argument arg2 = new Argument(restEntity.entity.getClassName(), "domainObject");
    GMethod to = bindingMapper.getMethod("toDomain", arg1, arg2);
    to.setStatic();

    this.copyPrimitivePropertiesToDomain(to, restEntity);
    this.copyManyToOnePropertiesToDomain(bindingMapper, to, restEntity);
    this.copyOneToManyPropertiesToDomain(bindingMapper, to, restEntity);
    this.copyManyToManyPropertiesToDomain(bindingMapper, to, restEntity);

    bindingMapper.addImports(restEntity.getBindingClassName(), restEntity.entity.getFullClassName());
  }

  private void copyPrimitivePropertiesToDomain(GMethod to, RestEntity restEntity) {
    for (PrimitiveProperty p : restEntity.getPrimitivePropertiesIncludingInherited()) {
      if (p.getVariableName().equals("id") || p.getVariableName().equals("version")) {
        continue;
      }
      to.body.line("domainObject.set{}(binding.{});", p.getCapitalVariableName(), p.getVariableName());
    }
  }

  private void copyManyToOnePropertiesToDomain(GClass bindingMapper, GMethod to, RestEntity restEntity) {
    for (ManyToOneProperty p : restEntity.getManyToOnePropertiesIncludingInherited()) {
      if (p.getOneSide().isCodeEntity()) {
        to.body.line(
          "domainObject.set{}(binding.{} == null ? null : {}.fromCode(binding.{}));",
          p.getJavaType(),
          p.getVariableName(),
          p.getCapitalVariableName(),
          p.getVariableName());
      } else {
        to.body.line(
          "domainObject.set{}(binding.{} == null ? null : binding.{}.getId() == null ? null : {}.queries.find(binding.{}.getId()));",
          p.getCapitalVariableName(),
          p.getVariableName(),
          p.getVariableName(),
          p.getJavaType(),
          p.getVariableName());
      }
      bindingMapper.addImports(p.getOneSide().getFullClassName());
    }
  }

  private void copyOneToManyPropertiesToDomain(GClass bindingMapper, GMethod to, RestEntity restEntity) {
    for (OneToManyProperty p : restEntity.getOneToManyPropertiesIncludingInherited()) {
      if (p.isCollectionSkipped() || p.isManyToMany()) {
        continue;
      }
      if (p.isOneToOne()) {
        to.body.line(
          "domainObject.set{}(binding.{} == null ? null : binding.{}.getId() == null ? null : {}.queries.find(binding.{}.getId()));",
          p.getCapitalVariableNameSingular(),
          p.getVariableName(),
          p.getVariableName(),
          p.getManySide().getClassName(),
          p.getVariableName());
      } else {
        to.body.line("final " + p.getJavaType() + " " + p.getVariableName() + " = new ArrayList<" + p.getManySide().getClassName() + ">();");
        to.body.line("if (binding.{} != null) {", p.getVariableName());
        to.body.line("_   for (final Link l : binding.{}.getLinks()) {", p.getVariableName());
        to.body.line("_   _   {} o = l.getId() == null ? null : {}.queries.find(l.getId());",//
          p.getManySide().getClassName(),
          p.getManySide().getClassName());
        to.body.line("_   _   {}.add(o);", p.getVariableName());
        to.body.line("_   }");
        to.body.line("}");
        to.body.line("domainObject.set{}({});", p.getCapitalVariableName(), p.getVariableName());
        bindingMapper.addImports(List.class, ArrayList.class);
      }
      bindingMapper.addImports(p.getManySide().getFullClassName());
      bindingMapper.addImports(Link.class);
    }
  }

  private void copyManyToManyPropertiesToDomain(GClass bindingMapper, GMethod to, RestEntity restEntity) {
    for (ManyToManyProperty p : restEntity.getManyToManyPropertiesIncludingInherited()) {
      if (p.getMySideOneToMany().isCollectionSkipped()) {
        continue;
      }
      to.body.line("final " + p.getJavaType() + " " + p.getVariableName() + " = new ArrayList<" + p.getTargetJavaType() + ">();");
      to.body.line("for (final Link l : binding.{}.getLinks()) {", p.getVariableName());
      to.body.line("_   {} o = l.getId() == null ? null : {}.queries.find(l.getId());", p.getTargetJavaType(), p.getTargetJavaType());
      to.body.line("_   {}.add(o);", p.getVariableName());
      to.body.line("}");
      to.body.line("domainObject.set{}({});", p.getCapitalVariableName(), p.getVariableName());
      bindingMapper.addImports(List.class, ArrayList.class);
    }
  }
}
