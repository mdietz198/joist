package features.rs.domain;

import static features.domain.builders.Builders.aCodeADomainObject;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import joist.domain.uow.UoW;
import joist.rs.LinkCollection;

import org.junit.Before;
import org.junit.Test;

import features.domain.AbstractFeaturesTest;
import features.domain.builders.CodeADomainObjectBuilder;
import features.rs.resources.CodeADomainObjectResourceCollectionCodegen;

public class CodeADomainObjectQueryParameterFilteringTest extends AbstractFeaturesTest {

  CodeADomainObjectResourceCollectionCodegen resource = new CodeADomainObjectResourceCollectionCodegen();

  CodeADomainObjectBuilder objectWithCode;

  @Before
  public void setUp() {
    super.setUp();
    this.objectWithCode = aCodeADomainObject().blue().defaults();
    aCodeADomainObject().green().defaults();
    this.commitAndReOpen();
    UoW.close();
  }

  @Test
  public void testGetWithCodeFilter() {
    LinkCollection GrandChildren = this.resource.get(repo, null, "GREEN", null);
    assertThat(GrandChildren.getLinks().size(), is(1));
  }
}
