package features.rs.mappers;

import features.domain.InheritanceAOwner;
import features.domain.InheritanceASubTwo;
import features.domain.InheritanceAThing;
import features.rs.binding.InheritanceASubTwoBinding;
import joist.rs.ObjectLinkBinding;

public class InheritanceASubTwoBindingMapper {

  private InheritanceASubTwoBindingMapper() {
  }

  public static InheritanceASubTwoBinding toBinding(InheritanceASubTwo domainObject) {
    InheritanceASubTwoBinding binding = new InheritanceASubTwoBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.two = domainObject.getTwo();
    binding.inheritanceAOwner = domainObject.getInheritanceAOwner() == null ? null : new ObjectLinkBinding(domainObject.getInheritanceAOwner());
    binding.inheritanceAThing = domainObject.getInheritanceAThing() == null ? null : new ObjectLinkBinding(domainObject.getInheritanceAThing());
    return binding;
  }

  public static void toDomain(InheritanceASubTwoBinding binding, InheritanceASubTwo domainObject) {
    domainObject.setName(binding.name);
    domainObject.setTwo(binding.two);
    domainObject.setInheritanceAOwner(binding.inheritanceAOwner == null ? null : binding.inheritanceAOwner.getId() == null ? null : InheritanceAOwner.queries.find(binding.inheritanceAOwner.getId()));
    domainObject.setInheritanceAThing(binding.inheritanceAThing == null ? null : binding.inheritanceAThing.getId() == null ? null : InheritanceAThing.queries.find(binding.inheritanceAThing.getId()));
  }

}
