package features.rs.mappers;

import features.domain.ChildG;
import features.domain.ParentG;
import features.rs.binding.ParentGBinding;
import joist.rs.ObjectLinkBinding;

public class ParentGBindingMapper {

  private ParentGBindingMapper() {
  }

  public static ParentGBinding toBinding(ParentG domainObject) {
    ParentGBinding binding = new ParentGBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentOneChildGs = domainObject.getParentOneChildG() == null ? null : new ObjectLinkBinding(domainObject.getParentOneChildG());
    binding.parentTwoChildGs = domainObject.getParentTwoChildG() == null ? null : new ObjectLinkBinding(domainObject.getParentTwoChildG());
    return binding;
  }

  public static void toDomain(ParentGBinding binding, ParentG domainObject) {
    domainObject.setName(binding.name);
    domainObject.setParentOneChildG(binding.parentOneChildGs == null ? null : binding.parentOneChildGs.getId() == null ? null : ChildG.queries.find(binding.parentOneChildGs.getId()));
    domainObject.setParentTwoChildG(binding.parentTwoChildGs == null ? null : binding.parentTwoChildGs.getId() == null ? null : ChildG.queries.find(binding.parentTwoChildGs.getId()));
  }

}
