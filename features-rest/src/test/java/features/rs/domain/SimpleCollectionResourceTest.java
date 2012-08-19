package features.rs.domain;

import joist.domain.uow.UoW;
import junit.framework.Assert;

import org.junit.Test;

import features.Registry;
import features.domain.AbstractFeaturesTest;
import features.domain.Child;
import features.rs.binding.ChildBinding;
import features.rs.resources.ChildResourceCollectionCodegen;

public class SimpleCollectionResourceTest extends AbstractFeaturesTest {

  ChildResourceCollectionCodegen resource = new ChildResourceCollectionCodegen();

  @Test
  public void testPostChild() {
    UoW.close();
    ChildBinding binding = new ChildBinding();
    binding.name = "new child";
    Long id = this.resource.post(binding);
    UoW.open(Registry.getRepository(), null);
    Assert.assertEquals(Child.queries.find(id).getName(), "new child");
  }
}
