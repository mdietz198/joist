package features.rs.domain;

import static features.domain.builders.Builders.aParent;
import joist.domain.uow.UoW;
import joist.rs.Link;
import junit.framework.Assert;

import org.junit.Test;

import features.Registry;
import features.domain.AbstractFeaturesTest;
import features.domain.Child;
import features.domain.builders.ParentBuilder;
import features.rs.binding.ChildBinding;
import features.rs.resources.ChildResourceCollectionCodegen;

public class SimpleCollectionResourceTest extends AbstractFeaturesTest {

  ChildResourceCollectionCodegen resource = new ChildResourceCollectionCodegen();

  @Test
  public void testPostChild() {
    ParentBuilder p = aParent().defaults();
    this.commitAndReOpen();

    ChildBinding binding = new ChildBinding();
    binding.name = "new child";
    binding.parent = new Link(p.get());

    UoW.close();
    Long id = this.resource.post(repo, binding);
    UoW.open(Registry.getRepository(), null);
    Assert.assertEquals(Child.queries.find(id).getName(), "new child");
  }
}
