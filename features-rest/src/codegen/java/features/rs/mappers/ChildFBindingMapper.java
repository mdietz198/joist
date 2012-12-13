package features.rs.mappers;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.ChildF;
import features.rs.binding.ChildFBinding;
import features.rs.resources.ParentFResourceCollectionCodegen;
import joist.rs.CollectionLinkBinding;

public class ChildFBindingMapper {

  private ChildFBindingMapper() {
  }

  public static ChildFBinding toBinding(ChildF domainObject) {
    ChildFBinding binding = new ChildFBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.childOneParentFs = new CollectionLinkBinding("childOneParentFs", new UriBuilderImpl().path(ParentFResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("childF", domainObject.getId()).build().toString());
    binding.childTwoParentFs = new CollectionLinkBinding("childTwoParentFs", new UriBuilderImpl().path(ParentFResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("childF", domainObject.getId()).build().toString());
    return binding;
  }

  public static void toDomain(ChildFBinding binding, ChildF domainObject) {
    domainObject.setName(binding.name);
  }

}
