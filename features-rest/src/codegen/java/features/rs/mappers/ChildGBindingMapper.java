package features.rs.mappers;

import features.domain.ChildG;
import features.domain.ParentG;
import features.rs.binding.ChildGBinding;
import joist.rs.ObjectLinkBinding;

public class ChildGBindingMapper {

  private ChildGBindingMapper() {
  }

  public static ChildGBinding toBinding(ChildG domainObject) {
    ChildGBinding binding = new ChildGBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentOne = domainObject.getParentOne() == null ? null : new ObjectLinkBinding(domainObject.getParentOne());
    binding.parentTwo = domainObject.getParentTwo() == null ? null : new ObjectLinkBinding(domainObject.getParentTwo());
    return binding;
  }

  public static void toDomain(ChildGBinding binding, ChildG domainObject) {
    domainObject.setName(binding.name);
    domainObject.setParentOne(binding.parentOne == null ? null : binding.parentOne.getId() == null ? null : ParentG.queries.find(binding.parentOne.getId()));
    domainObject.setParentTwo(binding.parentTwo == null ? null : binding.parentTwo.getId() == null ? null : ParentG.queries.find(binding.parentTwo.getId()));
  }

}
