package features.rs.mappers;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.ChildIB;
import features.domain.ParentI;
import features.rs.binding.ParentIBinding;
import features.rs.resources.ChildIAResourceCollectionCodegen;
import joist.rs.CollectionLinkBinding;
import joist.rs.ObjectLinkBinding;

public class ParentIBindingMapper {

  private ParentIBindingMapper() {
  }

  public static ParentIBinding toBinding(ParentI domainObject) {
    ParentIBinding binding = new ParentIBinding();
    binding.id = domainObject.getId();
    binding.version = domainObject.getVersion();
    binding.childAs = new CollectionLinkBinding("childAs", new UriBuilderImpl().path(ChildIAResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("parentI", domainObject.getId()).build().toString());
    binding.childBs = domainObject.getChildB() == null ? null : new ObjectLinkBinding(domainObject.getChildB());
    return binding;
  }

  public static void toDomain(ParentIBinding binding, ParentI domainObject) {
    domainObject.setChildB(binding.childBs == null ? null : binding.childBs.getId() == null ? null : ChildIB.queries.find(binding.childBs.getId()));
  }

}
