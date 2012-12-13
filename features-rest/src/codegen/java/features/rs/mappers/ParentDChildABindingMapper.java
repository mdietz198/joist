package features.rs.mappers;

import features.domain.ParentD;
import features.domain.ParentDChildA;
import features.rs.binding.ParentDChildABinding;
import joist.rs.ObjectLinkBinding;

public class ParentDChildABindingMapper {

  private ParentDChildABindingMapper() {
  }

  public static ParentDChildABinding toBinding(ParentDChildA domainObject) {
    ParentDChildABinding binding = new ParentDChildABinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentD = domainObject.getParentD() == null ? null : new ObjectLinkBinding(domainObject.getParentD());
    return binding;
  }

  public static void toDomain(ParentDChildABinding binding, ParentDChildA domainObject) {
    domainObject.setName(binding.name);
    domainObject.setParentD(binding.parentD == null ? null : binding.parentD.getId() == null ? null : ParentD.queries.find(binding.parentD.getId()));
  }

}
