package features.rs.mappers;

import features.domain.InheritanceBRoot;
import features.domain.InheritanceBRootChild;
import features.rs.binding.InheritanceBRootChildBinding;
import joist.rs.ObjectLinkBinding;

public class InheritanceBRootChildBindingMapper {

  private InheritanceBRootChildBindingMapper() {
  }

  public static InheritanceBRootChildBinding toBinding(InheritanceBRootChild domainObject) {
    InheritanceBRootChildBinding binding = new InheritanceBRootChildBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.inheritanceBRoot = domainObject.getInheritanceBRoot() == null ? null : new ObjectLinkBinding(domainObject.getInheritanceBRoot());
    return binding;
  }

  public static void toDomain(InheritanceBRootChildBinding binding, InheritanceBRootChild domainObject) {
    domainObject.setName(binding.name);
    domainObject.setInheritanceBRoot(binding.inheritanceBRoot == null ? null : binding.inheritanceBRoot.getId() == null ? null : InheritanceBRoot.queries.find(binding.inheritanceBRoot.getId()));
  }

}
