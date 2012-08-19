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
import features.rs.binding.InheritanceASubTwoBinding;
import features.rs.helpers.BindingMapper;
import features.rs.resources.InheritanceASubTwoResourceCodegen;

public class PutManyToOneReferenceResourceTest extends AbstractFeaturesTest {
  InheritanceASubTwoResourceCodegen resource = new InheritanceASubTwoResourceCodegen();

  @Test
  public void testPutAssignsInheritanceASubTwoDomainObjectReferenceById() {
    InheritanceASubTwoBuilder object = aInheritanceASubTwo().defaults();
    Assert.assertNull(object.inheritanceAThing());
    InheritanceAThingBuilder manyToOneReferencedObject = aInheritanceAThing().defaults();
    this.commitAndReOpen();

    Long id = object.id();
    UoW.close();
    InheritanceASubTwoBinding binding = BindingMapper.toBinding(object.get());
    Link reference = new Link();
    reference.setId(manyToOneReferencedObject.id());
    binding.inheritanceAThing = reference;

    this.resource.put(repo, id, binding);

    UoW.open(Registry.getRepository(), null);
    Assert.assertEquals(object.inheritanceAThing(), manyToOneReferencedObject);
  }
}
