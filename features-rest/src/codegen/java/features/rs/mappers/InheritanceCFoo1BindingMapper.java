package features.rs.mappers;

import features.domain.InheritanceCFoo1;
import features.rs.binding.InheritanceCFoo1Binding;

public class InheritanceCFoo1BindingMapper {

  private InheritanceCFoo1BindingMapper() {
  }

  public static InheritanceCFoo1Binding toBinding(InheritanceCFoo1 domainObject) {
    InheritanceCFoo1Binding binding = new InheritanceCFoo1Binding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.foo = domainObject.getFoo();
    return binding;
  }

  public static void toDomain(InheritanceCFoo1Binding binding, InheritanceCFoo1 domainObject) {
    domainObject.setName(binding.name);
    domainObject.setFoo(binding.foo);
  }

}
