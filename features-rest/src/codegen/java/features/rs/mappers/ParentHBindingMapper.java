package features.rs.mappers;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.ParentH;
import features.rs.binding.ParentHBinding;
import features.rs.resources.ChildHResourceCollectionCodegen;
import joist.rs.CollectionLinkBinding;

public class ParentHBindingMapper {

  private ParentHBindingMapper() {
  }

  public static ParentHBinding toBinding(ParentH domainObject) {
    ParentHBinding binding = new ParentHBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.threshold = domainObject.getThreshold();
    binding.version = domainObject.getVersion();
    binding.childHs = new CollectionLinkBinding("childHs", new UriBuilderImpl().path(ChildHResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("parentH", domainObject.getId()).build().toString());
    return binding;
  }

  public static void toDomain(ParentHBinding binding, ParentH domainObject) {
    domainObject.setName(binding.name);
    domainObject.setThreshold(binding.threshold);
  }

}
