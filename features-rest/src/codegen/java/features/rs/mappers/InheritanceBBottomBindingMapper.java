package features.rs.mappers;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.InheritanceBBottom;
import features.rs.binding.InheritanceBBottomBinding;
import features.rs.resources.InheritanceBRootChildResourceCollectionCodegen;
import joist.rs.CollectionLinkBinding;

public class InheritanceBBottomBindingMapper {

  private InheritanceBBottomBindingMapper() {
  }

  public static InheritanceBBottomBinding toBinding(InheritanceBBottom domainObject) {
    InheritanceBBottomBinding binding = new InheritanceBBottomBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.middleName = domainObject.getMiddleName();
    binding.bottomName = domainObject.getBottomName();
    binding.inheritanceBRootChilds = new CollectionLinkBinding("inheritanceBRootChilds", new UriBuilderImpl().path(InheritanceBRootChildResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("inheritanceBBottom", domainObject.getId()).build().toString());
    return binding;
  }

  public static void toDomain(InheritanceBBottomBinding binding, InheritanceBBottom domainObject) {
    domainObject.setName(binding.name);
    domainObject.setMiddleName(binding.middleName);
    domainObject.setBottomName(binding.bottomName);
  }

}
