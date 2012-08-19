package features.rs.domain;

import static features.domain.builders.Builders.aManyToManyABar;
import static features.domain.builders.Builders.aManyToManyAFoo;
import joist.domain.uow.UoW;
import joist.rs.Link;

import org.junit.Assert;
import org.junit.Test;

import features.Registry;
import features.domain.AbstractFeaturesTest;
import features.domain.builders.ManyToManyABarBuilder;
import features.domain.builders.ManyToManyAFooBuilder;
import features.rs.binding.ManyToManyAFooBinding;
import features.rs.helpers.BindingMapper;
import features.rs.resources.ManyToManyAFooResourceCodegen;

public class PutManyToManyReferenceResourceTest extends AbstractFeaturesTest {

  ManyToManyAFooResourceCodegen resource = new ManyToManyAFooResourceCodegen();

  @Test
  public void testPutAssignsManyToManyAFooDomainObjectReferenceById() {
    ManyToManyAFooBuilder object = aManyToManyAFoo().defaults();
    ManyToManyABarBuilder reference1 = aManyToManyABar().defaults();
    ManyToManyABarBuilder reference2 = aManyToManyABar().defaults();
    Assert.assertEquals(object.manyToManyABars().size(), 0);
    this.commitAndReOpen();

    Long id = object.id();
    ManyToManyAFooBinding binding = BindingMapper.toBinding(object.get());
    Link linkToReference1 = new Link(reference1.get());
    Link linkToReference2 = new Link(reference2.get());
    binding.manyToManyABars.getLinks().add(linkToReference1);
    binding.manyToManyABars.getLinks().add(linkToReference2);

    UoW.close();
    this.resource.put(repo, id, binding);

    UoW.open(Registry.getRepository(), null);
    Assert.assertEquals(object.manyToManyABar(0), reference1);
    Assert.assertEquals(object.manyToManyABar(1), reference2);
  }
}
