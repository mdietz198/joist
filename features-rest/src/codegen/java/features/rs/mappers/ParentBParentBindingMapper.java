package features.rs.mappers;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.ParentBParent;
import features.rs.binding.ParentBParentBinding;
import features.rs.resources.ParentBChildBarResourceCollectionCodegen;
import features.rs.resources.ParentBChildFooResourceCollectionCodegen;
import joist.rs.CollectionLinkBinding;

public class ParentBParentBindingMapper {

  private ParentBParentBindingMapper() {
  }

  public static ParentBParentBinding toBinding(ParentBParent domainObject) {
    ParentBParentBinding binding = new ParentBParentBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentBChildBars = new CollectionLinkBinding("parentBChildBars", new UriBuilderImpl().path(ParentBChildBarResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("parentBParent", domainObject.getId()).build().toString());
    binding.parentBChildFoos = new CollectionLinkBinding("parentBChildFoos", new UriBuilderImpl().path(ParentBChildFooResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("parentBParent", domainObject.getId()).build().toString());
    return binding;
  }

  public static void toDomain(ParentBParentBinding binding, ParentBParent domainObject) {
    domainObject.setName(binding.name);
  }

}
