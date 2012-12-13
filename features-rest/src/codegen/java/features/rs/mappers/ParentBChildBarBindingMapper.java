package features.rs.mappers;

import features.domain.ParentBChildBar;
import features.domain.ParentBParent;
import features.rs.binding.ParentBChildBarBinding;
import joist.rs.ObjectLinkBinding;

public class ParentBChildBarBindingMapper {

  private ParentBChildBarBindingMapper() {
  }

  public static ParentBChildBarBinding toBinding(ParentBChildBar domainObject) {
    ParentBChildBarBinding binding = new ParentBChildBarBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentBParent = domainObject.getParentBParent() == null ? null : new ObjectLinkBinding(domainObject.getParentBParent());
    return binding;
  }

  public static void toDomain(ParentBChildBarBinding binding, ParentBChildBar domainObject) {
    domainObject.setName(binding.name);
    domainObject.setParentBParent(binding.parentBParent == null ? null : binding.parentBParent.getId() == null ? null : ParentBParent.queries.find(binding.parentBParent.getId()));
  }

}
