package features.rs.mappers;

import features.domain.ParentBChildBar;
import features.domain.ParentBChildZaz;
import features.domain.ParentBParent;
import features.rs.binding.ParentBChildZazBinding;
import joist.rs.ObjectLinkBinding;

public class ParentBChildZazBindingMapper {

  private ParentBChildZazBindingMapper() {
  }

  public static ParentBChildZazBinding toBinding(ParentBChildZaz domainObject) {
    ParentBChildZazBinding binding = new ParentBChildZazBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentBChildBar = domainObject.getParentBChildBar() == null ? null : new ObjectLinkBinding(domainObject.getParentBChildBar());
    binding.parentBParent = domainObject.getParentBParent() == null ? null : new ObjectLinkBinding(domainObject.getParentBParent());
    return binding;
  }

  public static void toDomain(ParentBChildZazBinding binding, ParentBChildZaz domainObject) {
    domainObject.setName(binding.name);
    domainObject.setParentBChildBar(binding.parentBChildBar == null ? null : binding.parentBChildBar.getId() == null ? null : ParentBChildBar.queries.find(binding.parentBChildBar.getId()));
    domainObject.setParentBParent(binding.parentBParent == null ? null : binding.parentBParent.getId() == null ? null : ParentBParent.queries.find(binding.parentBParent.getId()));
  }

}
