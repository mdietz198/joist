package features.rs.mappers;

import features.domain.ParentD;
import features.domain.ParentDChildC;
import features.domain.ParentDToChildC;
import features.rs.binding.ParentDToChildCBinding;
import joist.rs.ObjectLinkBinding;

public class ParentDToChildCBindingMapper {

  private ParentDToChildCBindingMapper() {
  }

  public static ParentDToChildCBinding toBinding(ParentDToChildC domainObject) {
    ParentDToChildCBinding binding = new ParentDToChildCBinding();
    binding.id = domainObject.getId();
    binding.version = domainObject.getVersion();
    binding.parentDChildC = domainObject.getParentDChildC() == null ? null : new ObjectLinkBinding(domainObject.getParentDChildC());
    binding.parentD = domainObject.getParentD() == null ? null : new ObjectLinkBinding(domainObject.getParentD());
    return binding;
  }

  public static void toDomain(ParentDToChildCBinding binding, ParentDToChildC domainObject) {
    domainObject.setParentDChildC(binding.parentDChildC == null ? null : binding.parentDChildC.getId() == null ? null : ParentDChildC.queries.find(binding.parentDChildC.getId()));
    domainObject.setParentD(binding.parentD == null ? null : binding.parentD.getId() == null ? null : ParentD.queries.find(binding.parentD.getId()));
  }

}
