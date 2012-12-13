package features.rs.mappers;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.ManyToManyBBar;
import features.rs.binding.ManyToManyBBarBinding;
import features.rs.resources.ManyToManyBFooToBarResourceCollectionCodegen;
import joist.rs.CollectionLinkBinding;

public class ManyToManyBBarBindingMapper {

  private ManyToManyBBarBindingMapper() {
  }

  public static ManyToManyBBarBinding toBinding(ManyToManyBBar domainObject) {
    ManyToManyBBarBinding binding = new ManyToManyBBarBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.ownedManyToManyBFooToBars = new CollectionLinkBinding("ownedManyToManyBFooToBars", new UriBuilderImpl().path(ManyToManyBFooToBarResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("manyToManyBBar", domainObject.getId()).build().toString());
    binding.ownerManyToManyBFoos = new CollectionLinkBinding("ownerManyToManyBFoos", new UriBuilderImpl().path(ManyToManyBFooToBarResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("manyToManyBBar", domainObject.getId()).build().toString());
    return binding;
  }

  public static void toDomain(ManyToManyBBarBinding binding, ManyToManyBBar domainObject) {
    domainObject.setName(binding.name);
  }

}
