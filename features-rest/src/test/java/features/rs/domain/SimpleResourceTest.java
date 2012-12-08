package features.rs.domain;

import static features.domain.builders.Builders.aChild;
import static features.domain.builders.Builders.aGrandChild;
import joist.domain.exceptions.NotFoundException;
import joist.domain.uow.UoW;
import joist.rs.ObjectLinkBinding;
import junit.framework.Assert;

import org.junit.Test;

import features.Registry;
import features.domain.AbstractFeaturesTest;
import features.domain.Child;
import features.domain.builders.ChildBuilder;
import features.rs.binding.ChildBinding;
import features.rs.resources.ChildResourceCodegen;

public class SimpleResourceTest extends AbstractFeaturesTest {

  ChildResourceCodegen resource = new ChildResourceCodegen();

  @Test
  public void testGetWithGrandChilds() {
    ChildBuilder child = aChild().defaults();
    aGrandChild().with(child).defaults();
    aGrandChild().with(child).defaults();
    this.commitAndReOpen();

    Long id = child.id();
    UoW.close();

    ChildBinding binding = this.resource.get(repo, id);
    Assert.assertEquals(binding.grandChilds.getLinks().size(), 2);
    Assert.assertEquals(binding.grandChilds.getLinks().get(0).getRelativeUrl(), "/grandChilds/1");
    Assert.assertEquals(binding.grandChilds.getLinks().get(1).getRelativeUrl(), "/grandChilds/2");
  }

  @Test
  public void testPutChild() {
    ChildBuilder child = aChild().defaults();
    this.commitAndReOpen();

    Long id = child.id();
    ChildBinding binding = new ChildBinding();
    binding.name = "new name";
    binding.parent = new ObjectLinkBinding(child.parent().get());

    UoW.close();
    this.resource.put(repo, id, binding);
    UoW.open(Registry.getRepository(), null);
    Assert.assertEquals(child.name(), "new name");
  }

  @Test(expected = NotFoundException.class)
  public void testDeleteChild() {
    ChildBuilder child = aChild().defaults();
    this.commitAndReOpen();

    Long id = child.id();
    UoW.close();
    this.resource.delete(repo, id);
    UoW.open(Registry.getRepository(), null);
    Child.queries.find(id);
  }

  @Test
  public void testDeleteChildIsIdempotent() {
    ChildBuilder child = aChild().defaults();
    this.commitAndReOpen();

    Long id = child.id();
    UoW.close();
    this.resource.delete(repo, id);
    // Second call returns cleanly
    this.resource.delete(repo, id);
  }

}
