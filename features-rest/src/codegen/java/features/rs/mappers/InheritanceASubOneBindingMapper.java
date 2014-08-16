package features.rs.mappers;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.InheritanceAOwner;
import features.domain.InheritanceASubOne;
import features.domain.InheritanceAThing;
import features.rs.binding.InheritanceASubOneBinding;
import features.rs.resources.InheritanceASubOneChildResourceCollectionCodegen;
import joist.rs.CollectionLinkBinding;
import joist.rs.ObjectLinkBinding;

public class InheritanceASubOneBindingMapper {

  private InheritanceASubOneBindingMapper() {
  }

  public static InheritanceASubOneBinding toBinding(InheritanceASubOne domainObject) {
    InheritanceASubOneBinding binding = new InheritanceASubOneBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.one = domainObject.getOne();
    binding.inheritanceAOwner = domainObject.getInheritanceAOwner() == null ? null : new ObjectLinkBinding(domainObject.getInheritanceAOwner());
    binding.inheritanceAThing = domainObject.getInheritanceAThing() == null ? null : new ObjectLinkBinding(domainObject.getInheritanceAThing());
    binding.inheritanceASubOneChilds = new CollectionLinkBinding("inheritanceASubOneChilds", new UriBuilderImpl().path(InheritanceASubOneChildResourceCollectionCodegen.class).queryParam("startIndex", 0).queryParam("maxResults", 20).queryParam("inheritanceASubOne", domainObject.getId()).build().toString());
    return binding;
  }

  public static void toDomain(InheritanceASubOneBinding binding, InheritanceASubOne domainObject) {
    domainObject.setName(binding.name);
    domainObject.setOne(binding.one);
    domainObject.setInheritanceAOwner(binding.inheritanceAOwner == null ? null : binding.inheritanceAOwner.getId() == null ? null : InheritanceAOwner.queries.find(binding.inheritanceAOwner.getId()));
    domainObject.setInheritanceAThing(binding.inheritanceAThing == null ? null : binding.inheritanceAThing.getId() == null ? null : InheritanceAThing.queries.find(binding.inheritanceAThing.getId()));
  }

}
