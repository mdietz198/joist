package features.rs.mappers;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.Child;
import features.domain.Parent;
import features.rs.binding.ChildBinding;
import features.rs.resources.GrandChildResourceCollectionCodegen;
import joist.rs.CollectionLinkBinding;
import joist.rs.ObjectLinkBinding;

public class ChildBindingMapper {

  private ChildBindingMapper() {
  }

  public static ChildBinding toBinding(Child domainObject) {
    ChildBinding binding = new ChildBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parent = domainObject.getParent() == null ? null : new ObjectLinkBinding(domainObject.getParent());
    binding.grandChilds = new CollectionLinkBinding("grandChilds", new UriBuilderImpl().path(GrandChildResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("child", domainObject.getId()).build().toString());
    return binding;
  }

  public static void toDomain(ChildBinding binding, Child domainObject) {
    domainObject.setName(binding.name);
    domainObject.setParent(binding.parent == null ? null : binding.parent.getId() == null ? null : Parent.queries.find(binding.parent.getId()));
  }

}
