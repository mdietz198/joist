package features.rs.mappers;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.ParentDChildC;
import features.rs.binding.ParentDChildCBinding;
import features.rs.resources.ParentDToChildCResourceCollectionCodegen;
import joist.rs.CollectionLinkBinding;

public class ParentDChildCBindingMapper {

  private ParentDChildCBindingMapper() {
  }

  public static ParentDChildCBinding toBinding(ParentDChildC domainObject) {
    ParentDChildCBinding binding = new ParentDChildCBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentDToChildCs = new CollectionLinkBinding("parentDToChildCs", new UriBuilderImpl().path(ParentDToChildCResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("parentDChildC", domainObject.getId()).build().toString());
    binding.parentDs = new CollectionLinkBinding("parentDs", new UriBuilderImpl().path(ParentDToChildCResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("parentDChildC", domainObject.getId()).build().toString());
    return binding;
  }

  public static void toDomain(ParentDChildCBinding binding, ParentDChildC domainObject) {
    domainObject.setName(binding.name);
  }

}
