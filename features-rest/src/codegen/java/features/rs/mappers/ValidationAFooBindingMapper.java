package features.rs.mappers;

import features.domain.ValidationAFoo;
import features.rs.binding.ValidationAFooBinding;

public class ValidationAFooBindingMapper {

  private ValidationAFooBindingMapper() {
  }

  public static ValidationAFooBinding toBinding(ValidationAFoo domainObject) {
    ValidationAFooBinding binding = new ValidationAFooBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    return binding;
  }

  public static void toDomain(ValidationAFooBinding binding, ValidationAFoo domainObject) {
    domainObject.setName(binding.name);
  }

}
