package features.rs.mappers;

import features.domain.ValuesA;
import features.rs.binding.ValuesABinding;

public class ValuesABindingMapper {

  private ValuesABindingMapper() {
  }

  public static ValuesABinding toBinding(ValuesA domainObject) {
    ValuesABinding binding = new ValuesABinding();
    binding.a = domainObject.getA();
    binding.b = domainObject.getB();
    binding.i = domainObject.getI();
    binding.id = domainObject.getId();
    binding.j = domainObject.getJ();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    return binding;
  }

  public static void toDomain(ValuesABinding binding, ValuesA domainObject) {
    domainObject.setA(binding.a);
    domainObject.setB(binding.b);
    domainObject.setI(binding.i);
    domainObject.setJ(binding.j);
    domainObject.setName(binding.name);
  }

}
