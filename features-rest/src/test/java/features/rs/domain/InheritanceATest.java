package features.rs.domain;

import org.junit.Assert;
import org.junit.Test;

import features.domain.AbstractFeaturesTest;
import features.domain.InheritanceASubTwo;

public class InheritanceATest extends AbstractFeaturesTest {

  @Test
  public void testInsertInheritanceASubTwo() {
    InheritanceASubTwo b = new InheritanceASubTwo();
    b.setName("name");
    b.setTwo("two");
    this.commitAndReOpen();
    Assert.assertEquals(1, b.getId().intValue());

    b = this.reload(b);
    Assert.assertEquals("name", b.getName());
    Assert.assertEquals("two", b.getTwo());
  }
}
