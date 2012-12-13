package features.rs.mappers;

import features.domain.ParentD;
import features.domain.ParentDChildB;
import features.rs.binding.ParentDChildBBinding;
import joist.rs.ObjectLinkBinding;

public class ParentDChildBBindingMapper {

  private ParentDChildBBindingMapper() {
  }

  public static ParentDChildBBinding toBinding(ParentDChildB domainObject) {
    ParentDChildBBinding binding = new ParentDChildBBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentD = domainObject.getParentD() == null ? null : new ObjectLinkBinding(domainObject.getParentD());
    return binding;
  }

  public static void toDomain(ParentDChildBBinding binding, ParentDChildB domainObject) {
    domainObject.setName(binding.name);
    domainObject.setParentD(binding.parentD == null ? null : binding.parentD.getId() == null ? null : ParentD.queries.find(binding.parentD.getId()));
  }

}
