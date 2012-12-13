package features.rs.mappers;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.ManyToManyABar;
import features.rs.binding.ManyToManyABarBinding;
import features.rs.resources.ManyToManyAFooToBarResourceCollectionCodegen;
import joist.rs.CollectionLinkBinding;

public class ManyToManyABarBindingMapper {

  private ManyToManyABarBindingMapper() {
  }

  public static ManyToManyABarBinding toBinding(ManyToManyABar domainObject) {
    ManyToManyABarBinding binding = new ManyToManyABarBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.manyToManyAFooToBars = new CollectionLinkBinding("manyToManyAFooToBars", new UriBuilderImpl().path(ManyToManyAFooToBarResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("manyToManyABar", domainObject.getId()).build().toString());
    binding.manyToManyAFoos = new CollectionLinkBinding("manyToManyAFoos", new UriBuilderImpl().path(ManyToManyAFooToBarResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("manyToManyABar", domainObject.getId()).build().toString());
    return binding;
  }

  public static void toDomain(ManyToManyABarBinding binding, ManyToManyABar domainObject) {
    domainObject.setName(binding.name);
  }

}
