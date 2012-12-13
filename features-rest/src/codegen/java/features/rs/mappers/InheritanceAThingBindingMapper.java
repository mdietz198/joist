package features.rs.mappers;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.InheritanceAThing;
import features.rs.binding.InheritanceAThingBinding;
import features.rs.resources.InheritanceASubOneResourceCollectionCodegen;
import features.rs.resources.InheritanceASubTwoResourceCollectionCodegen;
import joist.rs.CollectionLinkBinding;

public class InheritanceAThingBindingMapper {

  private InheritanceAThingBindingMapper() {
  }

  public static InheritanceAThingBinding toBinding(InheritanceAThing domainObject) {
    InheritanceAThingBinding binding = new InheritanceAThingBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.inheritanceASubOnes = new CollectionLinkBinding("inheritanceASubOnes", new UriBuilderImpl().path(InheritanceASubOneResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("inheritanceAThing", domainObject.getId()).build().toString());
    binding.inheritanceASubTwos = new CollectionLinkBinding("inheritanceASubTwos", new UriBuilderImpl().path(InheritanceASubTwoResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("inheritanceAThing", domainObject.getId()).build().toString());
    return binding;
  }

  public static void toDomain(InheritanceAThingBinding binding, InheritanceAThing domainObject) {
    domainObject.setName(binding.name);
  }

}
