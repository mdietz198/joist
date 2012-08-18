package features.rs.domain;

import static features.domain.builders.Builders.aChild;
import static features.domain.builders.Builders.aGrandChild;

import org.junit.Test;

import features.domain.AbstractFeaturesTest;
import features.domain.builders.ChildBuilder;

public class ChildTest extends AbstractFeaturesTest {

  @Test
  public void testWithGrandChilds() {
    ChildBuilder child = aChild().defaults();
    aGrandChild().with(child).defaults();
    aGrandChild().with(child).defaults();
    this.commitAndReOpen();
  }
}
