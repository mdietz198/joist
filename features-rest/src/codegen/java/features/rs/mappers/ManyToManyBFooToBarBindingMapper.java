package features.rs.mappers;

import features.domain.ManyToManyBBar;
import features.domain.ManyToManyBFoo;
import features.domain.ManyToManyBFooToBar;
import features.rs.binding.ManyToManyBFooToBarBinding;
import joist.rs.ObjectLinkBinding;

public class ManyToManyBFooToBarBindingMapper {

  private ManyToManyBFooToBarBindingMapper() {
  }

  public static ManyToManyBFooToBarBinding toBinding(ManyToManyBFooToBar domainObject) {
    ManyToManyBFooToBarBinding binding = new ManyToManyBFooToBarBinding();
    binding.id = domainObject.getId();
    binding.version = domainObject.getVersion();
    binding.owned = domainObject.getOwned() == null ? null : new ObjectLinkBinding(domainObject.getOwned());
    binding.ownerManyToManyBFoo = domainObject.getOwnerManyToManyBFoo() == null ? null : new ObjectLinkBinding(domainObject.getOwnerManyToManyBFoo());
    return binding;
  }

  public static void toDomain(ManyToManyBFooToBarBinding binding, ManyToManyBFooToBar domainObject) {
    domainObject.setOwned(binding.owned == null ? null : binding.owned.getId() == null ? null : ManyToManyBBar.queries.find(binding.owned.getId()));
    domainObject.setOwnerManyToManyBFoo(binding.ownerManyToManyBFoo == null ? null : binding.ownerManyToManyBFoo.getId() == null ? null : ManyToManyBFoo.queries.find(binding.ownerManyToManyBFoo.getId()));
  }

}
