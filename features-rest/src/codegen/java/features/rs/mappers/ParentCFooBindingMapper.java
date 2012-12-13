package features.rs.mappers;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.ParentCFoo;
import features.rs.binding.ParentCFooBinding;
import features.rs.resources.ParentCBarResourceCollectionCodegen;
import joist.rs.CollectionLinkBinding;

public class ParentCFooBindingMapper {

  private ParentCFooBindingMapper() {
  }

  public static ParentCFooBinding toBinding(ParentCFoo domainObject) {
    ParentCFooBinding binding = new ParentCFooBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.firstParentParentCBars = new CollectionLinkBinding("firstParentParentCBars", new UriBuilderImpl().path(ParentCBarResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("parentCFoo", domainObject.getId()).build().toString());
    binding.secondParentParentCBars = new CollectionLinkBinding("secondParentParentCBars", new UriBuilderImpl().path(ParentCBarResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("parentCFoo", domainObject.getId()).build().toString());
    return binding;
  }

  public static void toDomain(ParentCFooBinding binding, ParentCFoo domainObject) {
    domainObject.setName(binding.name);
  }

}
