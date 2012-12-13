package features.rs.mappers;

import features.domain.OneToOneABar;
import features.domain.OneToOneAFoo;
import features.rs.binding.OneToOneABarBinding;
import joist.rs.ObjectLinkBinding;

public class OneToOneABarBindingMapper {

  private OneToOneABarBindingMapper() {
  }

  public static OneToOneABarBinding toBinding(OneToOneABar domainObject) {
    OneToOneABarBinding binding = new OneToOneABarBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.oneToOneAFoo = domainObject.getOneToOneAFoo() == null ? null : new ObjectLinkBinding(domainObject.getOneToOneAFoo());
    return binding;
  }

  public static void toDomain(OneToOneABarBinding binding, OneToOneABar domainObject) {
    domainObject.setName(binding.name);
    domainObject.setOneToOneAFoo(binding.oneToOneAFoo == null ? null : binding.oneToOneAFoo.getId() == null ? null : OneToOneAFoo.queries.find(binding.oneToOneAFoo.getId()));
  }

}
