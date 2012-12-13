package features.rs.mappers;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.Parent;
import features.rs.binding.ParentBinding;
import features.rs.resources.ChildResourceCollectionCodegen;
import joist.rs.CollectionLinkBinding;

public class ParentBindingMapper {

  private ParentBindingMapper() {
  }

  public static ParentBinding toBinding(Parent domainObject) {
    ParentBinding binding = new ParentBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.childs = new CollectionLinkBinding("childs", new UriBuilderImpl().path(ChildResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("parent", domainObject.getId()).build().toString());
    return binding;
  }

  public static void toDomain(ParentBinding binding, Parent domainObject) {
    domainObject.setName(binding.name);
  }

}
