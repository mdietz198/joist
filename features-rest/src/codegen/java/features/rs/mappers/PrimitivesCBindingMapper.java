package features.rs.mappers;

import features.domain.PrimitivesC;
import features.rs.binding.PrimitivesCBinding;

public class PrimitivesCBindingMapper {

  private PrimitivesCBindingMapper() {
  }

  public static PrimitivesCBinding toBinding(PrimitivesC domainObject) {
    PrimitivesCBinding binding = new PrimitivesCBinding();
    binding.day = domainObject.getDay();
    binding.dollarAmount = domainObject.getDollarAmount();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.timestamp = domainObject.getTimestamp();
    binding.version = domainObject.getVersion();
    return binding;
  }

  public static void toDomain(PrimitivesCBinding binding, PrimitivesC domainObject) {
    domainObject.setDay(binding.day);
    domainObject.setDollarAmount(binding.dollarAmount);
    domainObject.setName(binding.name);
    domainObject.setTimestamp(binding.timestamp);
  }

}
