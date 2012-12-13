package features.rs.mappers;

import features.domain.ParentBChildFoo;
import features.domain.ParentBParent;
import features.rs.binding.ParentBChildFooBinding;
import joist.rs.ObjectLinkBinding;

public class ParentBChildFooBindingMapper {

  private ParentBChildFooBindingMapper() {
  }

  public static ParentBChildFooBinding toBinding(ParentBChildFoo domainObject) {
    ParentBChildFooBinding binding = new ParentBChildFooBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentBParent = domainObject.getParentBParent() == null ? null : new ObjectLinkBinding(domainObject.getParentBParent());
    return binding;
  }

  public static void toDomain(ParentBChildFooBinding binding, ParentBChildFoo domainObject) {
    domainObject.setName(binding.name);
    domainObject.setParentBParent(binding.parentBParent == null ? null : binding.parentBParent.getId() == null ? null : ParentBParent.queries.find(binding.parentBParent.getId()));
  }

}
