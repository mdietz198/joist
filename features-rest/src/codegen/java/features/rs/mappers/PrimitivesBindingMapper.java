package features.rs.mappers;

import features.domain.Primitives;
import features.rs.binding.PrimitivesBinding;

public class PrimitivesBindingMapper {

  private PrimitivesBindingMapper() {
  }

  public static PrimitivesBinding toBinding(Primitives domainObject) {
    PrimitivesBinding binding = new PrimitivesBinding();
    binding.flag = domainObject.getFlag();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    return binding;
  }

  public static void toDomain(PrimitivesBinding binding, Primitives domainObject) {
    domainObject.setFlag(binding.flag);
    domainObject.setName(binding.name);
  }

}
