package features.rs.mappers;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.ParentBChildBar;
import features.domain.ParentBParent;
import features.rs.binding.ParentBChildBarBinding;
import features.rs.resources.ParentBChildZazResourceCollectionCodegen;
import joist.rs.CollectionLinkBinding;
import joist.rs.ObjectLinkBinding;

public class ParentBChildBarBindingMapper {

  private ParentBChildBarBindingMapper() {
  }

  public static ParentBChildBarBinding toBinding(ParentBChildBar domainObject) {
    ParentBChildBarBinding binding = new ParentBChildBarBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentBParent = domainObject.getParentBParent() == null ? null : new ObjectLinkBinding(domainObject.getParentBParent());
    binding.parentBChildZazs = new CollectionLinkBinding("parentBChildZazs", new UriBuilderImpl().path(ParentBChildZazResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("parentBChildBar", domainObject.getId()).build().toString());
    return binding;
  }

  public static void toDomain(ParentBChildBarBinding binding, ParentBChildBar domainObject) {
    domainObject.setName(binding.name);
    domainObject.setParentBParent(binding.parentBParent == null ? null : binding.parentBParent.getId() == null ? null : ParentBParent.queries.find(binding.parentBParent.getId()));
  }

}
