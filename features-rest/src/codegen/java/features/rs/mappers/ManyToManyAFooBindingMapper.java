package features.rs.mappers;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.ManyToManyAFoo;
import features.rs.binding.ManyToManyAFooBinding;
import features.rs.resources.ManyToManyAFooToBarResourceCollectionCodegen;
import joist.rs.CollectionLinkBinding;

public class ManyToManyAFooBindingMapper {

  private ManyToManyAFooBindingMapper() {
  }

  public static ManyToManyAFooBinding toBinding(ManyToManyAFoo domainObject) {
    ManyToManyAFooBinding binding = new ManyToManyAFooBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.manyToManyAFooToBars = new CollectionLinkBinding("manyToManyAFooToBars", new UriBuilderImpl().path(ManyToManyAFooToBarResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("manyToManyAFoo", domainObject.getId()).build().toString());
    binding.manyToManyABars = new CollectionLinkBinding("manyToManyABars", new UriBuilderImpl().path(ManyToManyAFooToBarResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("manyToManyAFoo", domainObject.getId()).build().toString());
    return binding;
  }

  public static void toDomain(ManyToManyAFooBinding binding, ManyToManyAFoo domainObject) {
    domainObject.setName(binding.name);
  }

}
