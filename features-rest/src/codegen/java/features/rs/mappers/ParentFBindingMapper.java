package features.rs.mappers;

import features.domain.ChildF;
import features.domain.ParentF;
import features.rs.binding.ParentFBinding;
import joist.rs.ObjectLinkBinding;

public class ParentFBindingMapper {

  private ParentFBindingMapper() {
  }

  public static ParentFBinding toBinding(ParentF domainObject) {
    ParentFBinding binding = new ParentFBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.childOne = domainObject.getChildOne() == null ? null : new ObjectLinkBinding(domainObject.getChildOne());
    binding.childTwo = domainObject.getChildTwo() == null ? null : new ObjectLinkBinding(domainObject.getChildTwo());
    return binding;
  }

  public static void toDomain(ParentFBinding binding, ParentF domainObject) {
    domainObject.setName(binding.name);
    domainObject.setChildOne(binding.childOne == null ? null : binding.childOne.getId() == null ? null : ChildF.queries.find(binding.childOne.getId()));
    domainObject.setChildTwo(binding.childTwo == null ? null : binding.childTwo.getId() == null ? null : ChildF.queries.find(binding.childTwo.getId()));
  }

}
