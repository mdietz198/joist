package features.rs.mappers;

import features.domain.ValuesB;
import features.rs.binding.ValuesBBinding;

public class ValuesBBindingMapper {

  private ValuesBBindingMapper() {
  }

  public static ValuesBBinding toBinding(ValuesB domainObject) {
    ValuesBBinding binding = new ValuesBBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.start = domainObject.getStart();
    binding.version = domainObject.getVersion();
    return binding;
  }

  public static void toDomain(ValuesBBinding binding, ValuesB domainObject) {
    domainObject.setName(binding.name);
    domainObject.setStart(binding.start);
  }

}
