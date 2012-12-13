package features.rs.mappers;

import features.domain.Child;
import features.domain.GrandChild;
import features.rs.binding.GrandChildBinding;
import joist.rs.ObjectLinkBinding;

public class GrandChildBindingMapper {

  private GrandChildBindingMapper() {
  }

  public static GrandChildBinding toBinding(GrandChild domainObject) {
    GrandChildBinding binding = new GrandChildBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.child = domainObject.getChild() == null ? null : new ObjectLinkBinding(domainObject.getChild());
    return binding;
  }

  public static void toDomain(GrandChildBinding binding, GrandChild domainObject) {
    domainObject.setName(binding.name);
    domainObject.setChild(binding.child == null ? null : binding.child.getId() == null ? null : Child.queries.find(binding.child.getId()));
  }

}
