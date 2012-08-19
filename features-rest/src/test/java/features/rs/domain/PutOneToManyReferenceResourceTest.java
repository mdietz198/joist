package features.rs.domain;

import static features.domain.builders.Builders.aInheritanceASubTwo;
import static features.domain.builders.Builders.aInheritanceAThing;
import joist.domain.uow.UoW;
import joist.rs.Link;

import org.junit.Assert;
import org.junit.Test;

import features.Registry;
import features.domain.AbstractFeaturesTest;
import features.domain.builders.InheritanceASubTwoBuilder;
import features.domain.builders.InheritanceAThingBuilder;
import features.rs.binding.InheritanceAThingBinding;
import features.rs.helpers.BindingMapper;
import features.rs.resources.InheritanceAThingResourceCodegen;

public class PutOneToManyReferenceResourceTest extends AbstractFeaturesTest {
  InheritanceAThingResourceCodegen resource = new InheritanceAThingResourceCodegen();

  @Test
  public void testPutAssignsInheritanceASubTwoDomainObjectReferenceById() {
    InheritanceAThingBuilder object = aInheritanceAThing().defaults();
    InheritanceASubTwoBuilder reference1 = aInheritanceASubTwo().defaults();
    InheritanceASubTwoBuilder reference2 = aInheritanceASubTwo().defaults();
    Assert.assertNull(reference1.inheritanceAThing());
    Assert.assertNull(reference2.inheritanceAThing());
    this.commitAndReOpen();

    Long id = object.id();
    InheritanceAThingBinding binding = BindingMapper.toBinding(object.get());
    Link linkToReference1 = new Link(reference1.get());
    Link linkToReference2 = new Link(reference2.get());
    binding.inheritanceASubTwos.getLinks().add(linkToReference1);
    binding.inheritanceASubTwos.getLinks().add(linkToReference2);

    UoW.close();
    this.resource.put(id, binding);

    UoW.open(Registry.getRepository(), null);
    Assert.assertEquals(object.inheritanceASubTwo(0), reference1);
    Assert.assertEquals(object.inheritanceASubTwo(1), reference2);
  }
}
