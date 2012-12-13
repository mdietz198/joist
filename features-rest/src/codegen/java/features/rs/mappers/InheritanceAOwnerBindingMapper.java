package features.rs.mappers;

import features.domain.InheritanceAOwner;
import features.rs.binding.InheritanceAOwnerBinding;

public class InheritanceAOwnerBindingMapper {

  private InheritanceAOwnerBindingMapper() {
  }

  public static InheritanceAOwnerBinding toBinding(InheritanceAOwner domainObject) {
    InheritanceAOwnerBinding binding = new InheritanceAOwnerBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    return binding;
  }

  public static void toDomain(InheritanceAOwnerBinding binding, InheritanceAOwner domainObject) {
    domainObject.setName(binding.name);
  }

}
