package features.rs.mappers;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.ParentD;
import features.rs.binding.ParentDBinding;
import features.rs.resources.ParentDChildAResourceCollectionCodegen;
import features.rs.resources.ParentDChildBResourceCollectionCodegen;
import features.rs.resources.ParentDToChildCResourceCollectionCodegen;
import joist.rs.CollectionLinkBinding;

public class ParentDBindingMapper {

  private ParentDBindingMapper() {
  }

  public static ParentDBinding toBinding(ParentD domainObject) {
    ParentDBinding binding = new ParentDBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentDChildAs = new CollectionLinkBinding("parentDChildAs", new UriBuilderImpl().path(ParentDChildAResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("parentD", domainObject.getId()).build().toString());
    binding.parentDChildBs = new CollectionLinkBinding("parentDChildBs", new UriBuilderImpl().path(ParentDChildBResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("parentD", domainObject.getId()).build().toString());
    binding.parentDToChildCs = new CollectionLinkBinding("parentDToChildCs", new UriBuilderImpl().path(ParentDToChildCResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("parentD", domainObject.getId()).build().toString());
    binding.parentDChildCs = new CollectionLinkBinding("parentDChildCs", new UriBuilderImpl().path(ParentDToChildCResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("parentD", domainObject.getId()).build().toString());
    return binding;
  }

  public static void toDomain(ParentDBinding binding, ParentD domainObject) {
    domainObject.setName(binding.name);
  }

}
