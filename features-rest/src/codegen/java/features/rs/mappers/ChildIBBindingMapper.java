package features.rs.mappers;

import features.domain.ChildIB;
import features.domain.ParentI;
import features.rs.binding.ChildIBBinding;
import joist.rs.ObjectLinkBinding;

public class ChildIBBindingMapper {

  private ChildIBBindingMapper() {
  }

  public static ChildIBBinding toBinding(ChildIB domainObject) {
    ChildIBBinding binding = new ChildIBBinding();
    binding.id = domainObject.getId();
    binding.version = domainObject.getVersion();
    binding.parent = domainObject.getParent() == null ? null : new ObjectLinkBinding(domainObject.getParent());
    return binding;
  }

  public static void toDomain(ChildIBBinding binding, ChildIB domainObject) {
    domainObject.setParent(binding.parent == null ? null : binding.parent.getId() == null ? null : ParentI.queries.find(binding.parent.getId()));
  }

}
