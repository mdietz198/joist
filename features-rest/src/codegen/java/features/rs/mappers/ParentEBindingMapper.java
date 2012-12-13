package features.rs.mappers;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.ParentE;
import features.rs.binding.ParentEBinding;
import features.rs.resources.ParentEResourceCollectionCodegen;
import joist.rs.CollectionLinkBinding;
import joist.rs.ObjectLinkBinding;

public class ParentEBindingMapper {

  private ParentEBindingMapper() {
  }

  public static ParentEBinding toBinding(ParentE domainObject) {
    ParentEBinding binding = new ParentEBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentE = domainObject.getParentE() == null ? null : new ObjectLinkBinding(domainObject.getParentE());
    binding.parentEs = new CollectionLinkBinding("parentEs", new UriBuilderImpl().path(ParentEResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("parentE", domainObject.getId()).build().toString());
    return binding;
  }

  public static void toDomain(ParentEBinding binding, ParentE domainObject) {
    domainObject.setName(binding.name);
    domainObject.setParentE(binding.parentE == null ? null : binding.parentE.getId() == null ? null : ParentE.queries.find(binding.parentE.getId()));
  }

}
