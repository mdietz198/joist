package features.rs.resources;

import features.domain.InheritanceAOwner;
import features.rs.binding.InheritanceAOwnerBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import joist.domain.orm.Repository;
import joist.domain.uow.Block;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/inheritanceAOwners/{id}")
public class InheritanceAOwnerResourceCodegen {

  @GET
  @Produces({ "application/xml" })
  public InheritanceAOwnerBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<InheritanceAOwnerBinding>() {
      public InheritanceAOwnerBinding go() {
        return BindingMapper.toBinding(InheritanceAOwner.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final InheritanceAOwnerBinding inheritanceAOwner) {
    UoW.go(repo, null, new Block() {
      public void go() {
        BindingMapper.toDomain(inheritanceAOwner, InheritanceAOwner.queries.find(id));
      }
    });
  }

}
