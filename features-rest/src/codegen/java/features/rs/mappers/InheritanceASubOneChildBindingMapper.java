package features.rs.mappers;

import features.domain.InheritanceASubOne;
import features.domain.InheritanceASubOneChild;
import features.rs.binding.InheritanceASubOneChildBinding;
import joist.rs.ObjectLinkBinding;

public class InheritanceASubOneChildBindingMapper {

  private InheritanceASubOneChildBindingMapper() {
  }

  public static InheritanceASubOneChildBinding toBinding(InheritanceASubOneChild domainObject) {
    InheritanceASubOneChildBinding binding = new InheritanceASubOneChildBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.sub = domainObject.getSub() == null ? null : new ObjectLinkBinding(domainObject.getSub());
    return binding;
  }

  public static void toDomain(InheritanceASubOneChildBinding binding, InheritanceASubOneChild domainObject) {
    domainObject.setName(binding.name);
    domainObject.setSub(binding.sub == null ? null : binding.sub.getId() == null ? null : InheritanceASubOne.queries.find(binding.sub.getId()));
  }

}
