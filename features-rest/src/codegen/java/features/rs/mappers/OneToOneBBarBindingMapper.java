package features.rs.mappers;

import features.domain.OneToOneBBar;
import features.domain.OneToOneBFoo;
import features.rs.binding.OneToOneBBarBinding;
import joist.rs.ObjectLinkBinding;

public class OneToOneBBarBindingMapper {

  private OneToOneBBarBindingMapper() {
  }

  public static OneToOneBBarBinding toBinding(OneToOneBBar domainObject) {
    OneToOneBBarBinding binding = new OneToOneBBarBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.oneToOneBFoo = domainObject.getOneToOneBFoo() == null ? null : new ObjectLinkBinding(domainObject.getOneToOneBFoo());
    return binding;
  }

  public static void toDomain(OneToOneBBarBinding binding, OneToOneBBar domainObject) {
    domainObject.setName(binding.name);
    domainObject.setOneToOneBFoo(binding.oneToOneBFoo == null ? null : binding.oneToOneBFoo.getId() == null ? null : OneToOneBFoo.queries.find(binding.oneToOneBFoo.getId()));
  }

}
