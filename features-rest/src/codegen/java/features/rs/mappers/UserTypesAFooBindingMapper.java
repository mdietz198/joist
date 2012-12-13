package features.rs.mappers;

import features.domain.UserTypesAFoo;
import features.rs.binding.UserTypesAFooBinding;

public class UserTypesAFooBindingMapper {

  private UserTypesAFooBindingMapper() {
  }

  public static UserTypesAFooBinding toBinding(UserTypesAFoo domainObject) {
    UserTypesAFooBinding binding = new UserTypesAFooBinding();
    binding.created = domainObject.getCreated();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    return binding;
  }

  public static void toDomain(UserTypesAFooBinding binding, UserTypesAFoo domainObject) {
    domainObject.setCreated(binding.created);
    domainObject.setName(binding.name);
  }

}
