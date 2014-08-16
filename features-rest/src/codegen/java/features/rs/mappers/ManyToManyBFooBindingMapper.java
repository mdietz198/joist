package features.rs.mappers;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.ManyToManyBFoo;
import features.rs.binding.ManyToManyBFooBinding;
import features.rs.resources.ManyToManyBFooToBarResourceCollectionCodegen;
import joist.rs.CollectionLinkBinding;

public class ManyToManyBFooBindingMapper {

  private ManyToManyBFooBindingMapper() {
  }

  public static ManyToManyBFooBinding toBinding(ManyToManyBFoo domainObject) {
    ManyToManyBFooBinding binding = new ManyToManyBFooBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.manyToManyBFooToBars = new CollectionLinkBinding("manyToManyBFooToBars", new UriBuilderImpl().path(ManyToManyBFooToBarResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("manyToManyBFoo", domainObject.getId()).build().toString());
    binding.owneds = new CollectionLinkBinding("owneds", new UriBuilderImpl().path(ManyToManyBFooToBarResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("manyToManyBFoo", domainObject.getId()).build().toString());
    return binding;
  }

  public static void toDomain(ManyToManyBFooBinding binding, ManyToManyBFoo domainObject) {
    domainObject.setName(binding.name);
  }

}
