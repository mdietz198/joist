package features.rs.mappers;

import features.domain.ManyToManyABar;
import features.domain.ManyToManyAFoo;
import features.domain.ManyToManyAFooToBar;
import features.rs.binding.ManyToManyAFooToBarBinding;
import joist.rs.ObjectLinkBinding;

public class ManyToManyAFooToBarBindingMapper {

  private ManyToManyAFooToBarBindingMapper() {
  }

  public static ManyToManyAFooToBarBinding toBinding(ManyToManyAFooToBar domainObject) {
    ManyToManyAFooToBarBinding binding = new ManyToManyAFooToBarBinding();
    binding.id = domainObject.getId();
    binding.version = domainObject.getVersion();
    binding.manyToManyABar = domainObject.getManyToManyABar() == null ? null : new ObjectLinkBinding(domainObject.getManyToManyABar());
    binding.manyToManyAFoo = domainObject.getManyToManyAFoo() == null ? null : new ObjectLinkBinding(domainObject.getManyToManyAFoo());
    return binding;
  }

  public static void toDomain(ManyToManyAFooToBarBinding binding, ManyToManyAFooToBar domainObject) {
    domainObject.setManyToManyABar(binding.manyToManyABar == null ? null : binding.manyToManyABar.getId() == null ? null : ManyToManyABar.queries.find(binding.manyToManyABar.getId()));
    domainObject.setManyToManyAFoo(binding.manyToManyAFoo == null ? null : binding.manyToManyAFoo.getId() == null ? null : ManyToManyAFoo.queries.find(binding.manyToManyAFoo.getId()));
  }

}
