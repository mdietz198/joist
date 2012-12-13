package features.rs.mappers;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.OneToOneBFoo;
import features.rs.binding.OneToOneBFooBinding;
import features.rs.resources.OneToOneBBarResourceCollectionCodegen;
import joist.rs.CollectionLinkBinding;

public class OneToOneBFooBindingMapper {

  private OneToOneBFooBindingMapper() {
  }

  public static OneToOneBFooBinding toBinding(OneToOneBFoo domainObject) {
    OneToOneBFooBinding binding = new OneToOneBFooBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.oneToOneBBars = new CollectionLinkBinding("oneToOneBBars", new UriBuilderImpl().path(OneToOneBBarResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("oneToOneBFoo", domainObject.getId()).build().toString());
    return binding;
  }

  public static void toDomain(OneToOneBFooBinding binding, OneToOneBFoo domainObject) {
    domainObject.setName(binding.name);
  }

}
