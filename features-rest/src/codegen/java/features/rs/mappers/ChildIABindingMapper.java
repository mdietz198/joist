package features.rs.mappers;

import features.domain.ChildIA;
import features.domain.ParentI;
import features.rs.binding.ChildIABinding;
import joist.rs.ObjectLinkBinding;

public class ChildIABindingMapper {

  private ChildIABindingMapper() {
  }

  public static ChildIABinding toBinding(ChildIA domainObject) {
    ChildIABinding binding = new ChildIABinding();
    binding.id = domainObject.getId();
    binding.version = domainObject.getVersion();
    binding.parent = domainObject.getParent() == null ? null : new ObjectLinkBinding(domainObject.getParent());
    return binding;
  }

  public static void toDomain(ChildIABinding binding, ChildIA domainObject) {
    domainObject.setParent(binding.parent == null ? null : binding.parent.getId() == null ? null : ParentI.queries.find(binding.parent.getId()));
  }

}
