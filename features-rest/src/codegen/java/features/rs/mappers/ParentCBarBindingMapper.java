package features.rs.mappers;

import features.domain.ParentCBar;
import features.domain.ParentCFoo;
import features.rs.binding.ParentCBarBinding;
import joist.rs.ObjectLinkBinding;

public class ParentCBarBindingMapper {

  private ParentCBarBindingMapper() {
  }

  public static ParentCBarBinding toBinding(ParentCBar domainObject) {
    ParentCBarBinding binding = new ParentCBarBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.firstParent = domainObject.getFirstParent() == null ? null : new ObjectLinkBinding(domainObject.getFirstParent());
    binding.secondParent = domainObject.getSecondParent() == null ? null : new ObjectLinkBinding(domainObject.getSecondParent());
    return binding;
  }

  public static void toDomain(ParentCBarBinding binding, ParentCBar domainObject) {
    domainObject.setName(binding.name);
    domainObject.setFirstParent(binding.firstParent == null ? null : binding.firstParent.getId() == null ? null : ParentCFoo.queries.find(binding.firstParent.getId()));
    domainObject.setSecondParent(binding.secondParent == null ? null : binding.secondParent.getId() == null ? null : ParentCFoo.queries.find(binding.secondParent.getId()));
  }

}
