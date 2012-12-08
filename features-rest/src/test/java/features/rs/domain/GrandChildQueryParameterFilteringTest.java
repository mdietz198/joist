package features.rs.domain;

import static features.domain.builders.Builders.aChild;
import static features.domain.builders.Builders.aGrandChild;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import joist.domain.uow.UoW;
import joist.rs.PagedCollectionBinding;

import org.junit.Before;
import org.junit.Test;

import features.domain.AbstractFeaturesTest;
import features.domain.builders.ChildBuilder;
import features.domain.builders.GrandChildBuilder;
import features.rs.resources.GrandChildResourceCollectionCodegen;

public class GrandChildQueryParameterFilteringTest extends AbstractFeaturesTest {

  GrandChildResourceCollectionCodegen resource = new GrandChildResourceCollectionCodegen();

  ChildBuilder child;
  GrandChildBuilder grandChild;

  @Before
  public void setUp() {
    super.setUp();
    this.child = aChild().defaults();
    this.grandChild = aGrandChild().name("first").with(this.child).defaults();
    aGrandChild().name("second").with(aChild().defaults()).defaults();
    this.commitAndReOpen();
    UoW.close();
  }

  @Test
  public void testGetWithNoFilters() {
    PagedCollectionBinding grandChildren = this.resource.get(repo, null, null, null, null);
    assertThat(grandChildren.getLinks().size(), is(2));
  }

  @Test
  public void testGetWithNameFilter() {
    PagedCollectionBinding grandChildren = this.resource.get(repo, null, null, this.grandChild.name(), null);
    assertThat(grandChildren.getLinks().size(), is(1));
  }

  @Test
  public void testGetWithNameFilterNotFound() {
    PagedCollectionBinding grandChildren = this.resource.get(repo, null, null, "this name does not exist", null);
    assertThat(grandChildren.getLinks().size(), is(0));
  }

  @Test
  public void testGetWithChildFilter() {
    PagedCollectionBinding grandChildren = this.resource.get(repo, null, null, null, this.child.id());
    assertThat(grandChildren.getLinks().size(), is(1));
  }

  @Test
  public void testGetOnlyFirstItem() {
    PagedCollectionBinding grandChildren = this.resource.get(repo, 0, 1, null, null);
    assertThat(grandChildren.getLinks().size(), is(1));
    assertThat(grandChildren.getLinks().get(0).getId(), is(1L));
  }

  @Test
  public void testGetOnlySecondItem() {
    PagedCollectionBinding grandChildren = this.resource.get(repo, 1, 1, null, null);
    assertThat(grandChildren.getLinks().size(), is(1));
    assertThat(grandChildren.getLinks().get(0).getId(), is(2L));
  }

}
