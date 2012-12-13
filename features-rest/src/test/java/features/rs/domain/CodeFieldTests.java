package features.rs.domain;

import static features.domain.builders.Builders.aCodeADomainObject;
import static features.rs.mappers.CodeADomainObjectBindingMapper.toBinding;
import joist.domain.uow.UoW;

import org.junit.Assert;
import org.junit.Test;

import features.Registry;
import features.domain.AbstractFeaturesTest;
import features.domain.CodeAColor;
import features.domain.CodeASize;
import features.domain.builders.CodeADomainObjectBuilder;
import features.rs.binding.CodeADomainObjectBinding;
import features.rs.resources.CodeADomainObjectResourceCodegen;

public class CodeFieldTests extends AbstractFeaturesTest {

  CodeADomainObjectResourceCodegen resource = new CodeADomainObjectResourceCodegen();

  @Test
  public void testPutCodeADomainObject() {
    CodeADomainObjectBuilder object = aCodeADomainObject().defaults();
    object.codeAColor(CodeAColor.BLUE);
    object.codeASize(CodeASize.ONE);
    this.commitAndReOpen();

    Long id = object.id();
    UoW.close();
    CodeADomainObjectBinding binding = toBinding(object.get());
    binding.codeAColor = CodeAColor.GREEN.toString();
    binding.codeASize = CodeASize.TWO.toString();
    this.resource.put(repo, id, binding);

    UoW.open(Registry.getRepository(), null);
    Assert.assertEquals(object.codeAColor(), CodeAColor.GREEN);
    Assert.assertEquals(object.codeASize(), CodeASize.TWO);
  }
}
