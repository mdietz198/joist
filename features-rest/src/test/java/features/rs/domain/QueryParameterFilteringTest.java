package features.rs.domain;

import static features.domain.builders.Builders.aParent;
import joist.domain.uow.UoW;
import joist.rs.LinkCollection;
import junit.framework.Assert;

import org.junit.Test;

import features.domain.AbstractFeaturesTest;
import features.domain.builders.ParentBuilder;
import features.rs.resources.ParentResourceCollectionCodegen;

public class QueryParameterFilteringTest extends AbstractFeaturesTest {

  ParentResourceCollectionCodegen resource = new ParentResourceCollectionCodegen();

  @Test
  public void testNoFilter() {
    ParentBuilder mary = aParent().name("Mary").defaults();
    ParentBuilder sue = aParent().name("Sue").defaults();
    this.commitAndReOpen();

    UoW.close();
    LinkCollection result = this.resource.get(repo, null);
    Assert.assertEquals(2, result.getLinks().size());
    Assert.assertEquals(mary.id(), result.getLinks().get(0).getId());
    Assert.assertEquals(sue.id(), result.getLinks().get(1).getId());
  }

  @Test
  public void testFilterParentsByName() {
    aParent().name("Mary").defaults();
    ParentBuilder sue = aParent().name("Sue").defaults();
    this.commitAndReOpen();

    UoW.close();
    LinkCollection result = this.resource.get(repo, "Sue");
    Assert.assertEquals(1, result.getLinks().size());
    Assert.assertEquals(sue.id(), result.getLinks().get(0).getId());
  }
}
