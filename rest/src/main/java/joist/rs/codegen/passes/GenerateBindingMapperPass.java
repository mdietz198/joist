package joist.rs.codegen.passes;

import joist.codegen.dtos.Entity;
import joist.codegen.dtos.ManyToManyProperty;
import joist.codegen.dtos.ManyToOneProperty;
import joist.codegen.dtos.OneToManyProperty;
import joist.codegen.dtos.PrimitiveProperty;
import joist.codegen.passes.Pass;
import joist.rs.CollectionLinkBinding;
import joist.rs.ObjectLinkBinding;
import joist.rs.PagedCollectionBinding;
import joist.rs.codegen.RestCodegen;
import joist.rs.codegen.entities.RestEntity;
import joist.sourcegen.Argument;
import joist.sourcegen.GClass;
import joist.sourcegen.GMethod;

import com.sun.jersey.api.uri.UriBuilderImpl;

public class GenerateBindingMapperPass implements Pass<RestCodegen> {

  public void pass(RestCodegen codegen) {
    GClass bindingMapper = codegen.getOutputCodegenDirectory().getClass(codegen.getConfig().getRestHelpersPackage() + ".BindingMapper");
    bindingMapper.getConstructor().setPrivate();

    for (Entity entity : codegen.getSchema().getEntities().values()) {
      if (entity.isAbstract() || entity.isCodeEntity()) {
        continue;
      }
      RestEntity restEntity = new RestEntity(entity, codegen.getConfig());

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

    bindingMapper.addImports(ObjectLinkBinding.class, PagedCollectionBinding.class, UriBuilderImpl.class, CollectionLinkBinding.class);
    bindingMapper.addImports(
      restEntity.entity.getFullClassName(),
      restEntity.getFullBindingClassName(),
      restEntity.getFullResourceCollectionClassName());
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
        to.body.line("binding.{} = {} == null ? null : new ObjectLinkBinding({});", p.getVariableName(), domainGetter, domainGetter);
      }
    }
  }

  private void copyOneToManyPropertiesToBinding(GMethod to, RestEntity restEntity) {
    for (OneToManyProperty p : restEntity.getOneToManyPropertiesIncludingInherited()) {
      if (p.isOneToOne()) {
        String domainGetter = "domainObject.get" + p.getCapitalVariableNameSingular() + "()";
        to.body.line("binding.{} = {} == null ? null : new ObjectLinkBinding({});", p.getVariableName(), domainGetter, domainGetter);
      } else {
        this.addBindingCollectionAssignmentIfNeeded(to, p.getVariableName(), restEntity, new RestEntity(p.getManySide(), restEntity.getConfig()));
      }
    }
  }

  private void copyManyToManyPropertiesToBinding(GMethod to, RestEntity restEntity) {
    for (ManyToManyProperty p : restEntity.getManyToManyPropertiesIncludingInherited()) {
      this.addBindingCollectionAssignmentIfNeeded(to, p.getVariableName(), restEntity, new RestEntity(
        p.getMySideOneToMany().getManySide(),
        restEntity.getConfig()));
    }
  }

  private void addBindingCollectionAssignmentIfNeeded(GMethod to, String variableName, RestEntity oneSideEntity, RestEntity manySideEntity) {
    if (!manySideEntity.entity.isAbstract()) {
      to.body
        .line(
          "binding.{} = new CollectionLinkBinding(\"{}\", new UriBuilderImpl().path({}.class).queryParam(\"startIndex\", 0).queryParam(\"maxResults\", {}).queryParam(\"{}\", domainObject.getId()).build().toString());",
          variableName,
          variableName,
          manySideEntity.getResourceCollectionClassName(),
          oneSideEntity.getConfig().defaultMaxResults,
          oneSideEntity.entity.getVariableName());
    }
  }

  private void addToDomain(GClass bindingMapper, RestEntity restEntity) {
    Argument arg1 = new Argument(restEntity.getBindingClassName(), "binding");
    Argument arg2 = new Argument(restEntity.entity.getClassName(), "domainObject");
    GMethod to = bindingMapper.getMethod("toDomain", arg1, arg2);
    to.setStatic();

    this.copyPrimitivePropertiesToDomain(to, restEntity);
    this.copyManyToOnePropertiesToDomain(bindingMapper, to, restEntity);
    this.copyOneToOnePropertiesToDomain(bindingMapper, to, restEntity);

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

  private void copyOneToOnePropertiesToDomain(GClass bindingMapper, GMethod to, RestEntity restEntity) {
    for (OneToManyProperty p : restEntity.getOneToManyPropertiesIncludingInherited()) {
      if (p.isOneToOne()) {
        to.body.line(
          "domainObject.set{}(binding.{} == null ? null : binding.{}.getId() == null ? null : {}.queries.find(binding.{}.getId()));",
          p.getCapitalVariableNameSingular(),
          p.getVariableName(),
          p.getVariableName(),
          p.getManySide().getClassName(),
          p.getVariableName());
      }
      bindingMapper.addImports(p.getManySide().getFullClassName());
      bindingMapper.addImports(ObjectLinkBinding.class);
    }
  }
}
