package joist.rs.codegen.passes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import joist.codegen.dtos.Entity;
import joist.codegen.dtos.PrimitiveProperty;
import joist.codegen.passes.Pass;
import joist.rs.codegen.entities.RestEntity;
import joist.sourcegen.GClass;
import joist.sourcegen.GField;

public class GenerateBindingCodegenPass implements Pass {

  public void pass(joist.codegen.Codegen c) {
    // TODO Nasty hack to get my subclassed codegen
    joist.rs.codegen.Codegen codegen = (joist.rs.codegen.Codegen) c;
    for (Entity entity : codegen.getEntities().values()) {
      if (entity.isCodeEntity()) {
        continue;
      }

      RestEntity restEntity = new RestEntity(entity);
      GClass bindingCodegen = codegen.getOutputCodegenDirectory().getClass(restEntity.getFullBindingClassName());
      if (entity.isAbstract()) {
        bindingCodegen.setAbstract();
      }
      bindingCodegen.baseClassName(restEntity.getParentBindingClassName());
      this.annotations(bindingCodegen, restEntity);
      this.addPrimitiveProperties(bindingCodegen, restEntity);
    }
  }

  private void annotations(GClass resourceCodegen, RestEntity restEntity) {
    resourceCodegen.addAnnotation("@XmlRootElement(name=\"{}\")", restEntity.entity.getVariableName());
    resourceCodegen.addAnnotation("@XmlAccessorType(XmlAccessType.FIELD)");
    resourceCodegen.addImports(XmlRootElement.class, XmlAccessorType.class, XmlAccessType.class);
  }

  private void addPrimitiveProperties(GClass resourceCodegen, RestEntity restEntity) {
    for (PrimitiveProperty p : restEntity.entity.getPrimitiveProperties()) {
      GField field = resourceCodegen.getField(p.getVariableName()).setPublic();
      field.type(p.getJavaType());
    }
  }
}
