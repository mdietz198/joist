package features.rs.mappers;

import features.domain.PrimitivesB;
import features.rs.binding.PrimitivesBBinding;

public class PrimitivesBBindingMapper {

  private PrimitivesBBindingMapper() {
  }

  public static PrimitivesBBinding toBinding(PrimitivesB domainObject) {
    PrimitivesBBinding binding = new PrimitivesBBinding();
    binding.big1 = domainObject.getBig1();
    binding.big2 = domainObject.getBig2();
    binding.bool1 = domainObject.getBool1();
    binding.bool2 = domainObject.getBool2();
    binding.boolNullableWithDefaultFalse = domainObject.getBoolNullableWithDefaultFalse();
    binding.boolWithDefaultTrue = domainObject.getBoolWithDefaultTrue();
    binding.id = domainObject.getId();
    binding.int1 = domainObject.getInt1();
    binding.int2 = domainObject.getInt2();
    binding.small1 = domainObject.getSmall1();
    binding.small2 = domainObject.getSmall2();
    binding.version = domainObject.getVersion();
    return binding;
  }

  public static void toDomain(PrimitivesBBinding binding, PrimitivesB domainObject) {
    domainObject.setBig1(binding.big1);
    domainObject.setBig2(binding.big2);
    domainObject.setBool1(binding.bool1);
    domainObject.setBool2(binding.bool2);
    domainObject.setBoolNullableWithDefaultFalse(binding.boolNullableWithDefaultFalse);
    domainObject.setBoolWithDefaultTrue(binding.boolWithDefaultTrue);
    domainObject.setInt1(binding.int1);
    domainObject.setInt2(binding.int2);
    domainObject.setSmall1(binding.small1);
    domainObject.setSmall2(binding.small2);
  }

}
