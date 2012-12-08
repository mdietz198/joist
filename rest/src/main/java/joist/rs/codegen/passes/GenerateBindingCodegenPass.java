package joist.rs.codegen.passes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import joist.codegen.dtos.Entity;
import joist.codegen.dtos.ManyToManyProperty;
import joist.codegen.dtos.ManyToOneProperty;
import joist.codegen.dtos.OneToManyProperty;
import joist.codegen.dtos.PrimitiveProperty;
import joist.codegen.passes.Pass;
import joist.rs.ObjectLinkBinding;
import joist.rs.PagedCollectionBinding;
import joist.rs.codegen.RestCodegen;
import joist.rs.codegen.entities.RestEntity;
import joist.sourcegen.GClass;
import joist.sourcegen.GField;

public class GenerateBindingCodegenPass implements Pass<RestCodegen> {

  public void pass(RestCodegen codegen) {
    for (Entity entity : codegen.getSchema().getEntities().values()) {
      if (entity.isCodeEntity()) {
        continue;
      }

      RestEntity restEntity = new RestEntity(entity, codegen.getConfig());
      GClass bindingCodegen = codegen.getOutputCodegenDirectory().getClass(restEntity.getFullBindingClassName());
      if (entity.isAbstract()) {
        bindingCodegen.setAbstract();
      }
      bindingCodegen.baseClassName(restEntity.getParentBindingClassName());
      this.annotations(bindingCodegen, restEntity);
      this.primitiveProperties(bindingCodegen, restEntity);
      this.manyToOneProperties(bindingCodegen, restEntity);
      this.oneToManyProperties(bindingCodegen, restEntity);
      this.manyToManyProperties(bindingCodegen, restEntity);
    }
  }

  private void annotations(GClass resourceCodegen, RestEntity restEntity) {
    resourceCodegen.addAnnotation("@XmlRootElement(name=\"{}\")", restEntity.entity.getVariableName());
    resourceCodegen.addAnnotation("@XmlAccessorType(XmlAccessType.FIELD)");
    resourceCodegen.addImports(XmlRootElement.class, XmlAccessorType.class, XmlAccessType.class);
  }

  private void primitiveProperties(GClass resourceCodegen, RestEntity restEntity) {
    for (PrimitiveProperty p : restEntity.entity.getPrimitiveProperties()) {
      GField field = resourceCodegen.getField(p.getVariableName()).setPublic();
      field.type(p.getJavaType());
    }
  }

  private void manyToOneProperties(GClass bindingCodegen, RestEntity restEntity) {
    for (ManyToOneProperty p : restEntity.entity.getManyToOneProperties()) {
      GField field = bindingCodegen.getField(p.getVariableName()).setPublic();
      if (p.getOneSide().isCodeEntity()) {
        field.type(String.class);
      } else {
        field.type(ObjectLinkBinding.class);
        bindingCodegen.addImports(ObjectLinkBinding.class);
      }
    }
  }

  private void oneToManyProperties(GClass bindingCodegen, RestEntity restEntity) {
    for (OneToManyProperty p : restEntity.entity.getOneToManyProperties()) {
      if (p.isCollectionSkipped() || p.isManyToMany()) {
        continue;
      }

      GField field = bindingCodegen.getField(p.getVariableName()).setPublic();
      if (p.isOneToOne()) {
        field.type(ObjectLinkBinding.class);
        bindingCodegen.addImports(ObjectLinkBinding.class);
      } else {
        field.type(PagedCollectionBinding.class);
        bindingCodegen.addImports(PagedCollectionBinding.class);
      }
    }
  }

  private void manyToManyProperties(GClass bindingCodegen, RestEntity restEntity) {
    for (ManyToManyProperty p : restEntity.entity.getManyToManyProperties()) {
      if (p.getMySideOneToMany().isCollectionSkipped()) {
        continue;
      }

      GField field = bindingCodegen.getField(p.getVariableName()).setPublic();
      field.type(PagedCollectionBinding.class);
      bindingCodegen.addImports(PagedCollectionBinding.class);
    }
  }
}
