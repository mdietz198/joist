package features.rs.domain;

import static features.domain.builders.Builders.aParent;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import joist.domain.uow.UoW;
import joist.rs.ObjectLinkBinding;

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
    binding.parent = new ObjectLinkBinding(p.get());

    UoW.close();
    Long id = this.resource.post(repo, binding);
    UoW.open(Registry.getRepository(), null);
    assertThat(Child.queries.find(id).getName(), is("new child"));
  }
}
