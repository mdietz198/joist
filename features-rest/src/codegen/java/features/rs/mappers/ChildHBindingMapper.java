package features.rs.mappers;

import features.domain.ChildH;
import features.domain.ParentH;
import features.rs.binding.ChildHBinding;
import joist.rs.ObjectLinkBinding;

public class ChildHBindingMapper {

  private ChildHBindingMapper() {
  }

  public static ChildHBinding toBinding(ChildH domainObject) {
    ChildHBinding binding = new ChildHBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.quantity = domainObject.getQuantity();
    binding.version = domainObject.getVersion();
    binding.parent = domainObject.getParent() == null ? null : new ObjectLinkBinding(domainObject.getParent());
    return binding;
  }

  public static void toDomain(ChildHBinding binding, ChildH domainObject) {
    domainObject.setName(binding.name);
    domainObject.setQuantity(binding.quantity);
    domainObject.setParent(binding.parent == null ? null : binding.parent.getId() == null ? null : ParentH.queries.find(binding.parent.getId()));
  }

}
