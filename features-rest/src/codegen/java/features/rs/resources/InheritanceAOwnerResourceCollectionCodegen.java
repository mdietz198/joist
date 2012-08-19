package features.rs.resources;

import features.domain.InheritanceAOwner;
import features.rs.binding.InheritanceAOwnerBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.LinkCollection;

@Path("/inheritanceAOwners")
public class InheritanceAOwnerResourceCollectionCodegen {

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get(final @Context Repository repo) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, InheritanceAOwner.class, InheritanceAOwner.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final @Context Repository repo, final InheritanceAOwnerBinding inheritanceAOwner) {
    return UoW.go(repo, null, new BlockWithReturn<InheritanceAOwner>() {
      public InheritanceAOwner go() {
        InheritanceAOwner domainObject = new InheritanceAOwner();
        BindingMapper.toDomain(inheritanceAOwner, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
