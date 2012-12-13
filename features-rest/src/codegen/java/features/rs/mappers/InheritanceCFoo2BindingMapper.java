package features.rs.mappers;

import features.domain.InheritanceCFoo2;
import features.rs.binding.InheritanceCFoo2Binding;

public class InheritanceCFoo2BindingMapper {

  private InheritanceCFoo2BindingMapper() {
  }

  public static InheritanceCFoo2Binding toBinding(InheritanceCFoo2 domainObject) {
    InheritanceCFoo2Binding binding = new InheritanceCFoo2Binding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.foo = domainObject.getFoo();
    return binding;
  }

  public static void toDomain(InheritanceCFoo2Binding binding, InheritanceCFoo2 domainObject) {
    domainObject.setName(binding.name);
    domainObject.setFoo(binding.foo);
  }

}
