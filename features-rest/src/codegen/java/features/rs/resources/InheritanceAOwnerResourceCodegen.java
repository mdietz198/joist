package features.rs.resources;

import features.domain.InheritanceAOwner;
import features.rs.binding.InheritanceAOwnerBinding;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import joist.domain.exceptions.NotFoundException;
import joist.domain.orm.Repository;
import joist.domain.uow.Block;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.AbstractResource;
import static features.rs.mappers.InheritanceAOwnerBindingMapper.toBinding;
import static features.rs.mappers.InheritanceAOwnerBindingMapper.toDomain;

@Path("/inheritanceAOwners/{id}")
public class InheritanceAOwnerResourceCodegen extends AbstractResource<InheritanceAOwnerBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public InheritanceAOwnerBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<InheritanceAOwnerBinding>() {
      public InheritanceAOwnerBinding go() {
        return toBinding(InheritanceAOwner.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final InheritanceAOwnerBinding inheritanceAOwner) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(inheritanceAOwner, InheritanceAOwner.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          InheritanceAOwner.queries.delete(InheritanceAOwner.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
