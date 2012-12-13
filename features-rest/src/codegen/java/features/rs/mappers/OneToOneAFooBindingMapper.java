package features.rs.mappers;

import features.domain.OneToOneABar;
import features.domain.OneToOneAFoo;
import features.rs.binding.OneToOneAFooBinding;
import joist.rs.ObjectLinkBinding;

public class OneToOneAFooBindingMapper {

  private OneToOneAFooBindingMapper() {
  }

  public static OneToOneAFooBinding toBinding(OneToOneAFoo domainObject) {
    OneToOneAFooBinding binding = new OneToOneAFooBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.oneToOneABars = domainObject.getOneToOneABar() == null ? null : new ObjectLinkBinding(domainObject.getOneToOneABar());
    return binding;
  }

  public static void toDomain(OneToOneAFooBinding binding, OneToOneAFoo domainObject) {
    domainObject.setName(binding.name);
    domainObject.setOneToOneABar(binding.oneToOneABars == null ? null : binding.oneToOneABars.getId() == null ? null : OneToOneABar.queries.find(binding.oneToOneABars.getId()));
  }

}
