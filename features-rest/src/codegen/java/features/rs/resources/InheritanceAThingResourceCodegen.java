package features.rs.resources;

import features.domain.InheritanceAThing;
import features.rs.binding.InheritanceAThingBinding;
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
import static features.rs.mappers.InheritanceAThingBindingMapper.toBinding;
import static features.rs.mappers.InheritanceAThingBindingMapper.toDomain;

@Path("/inheritanceAThings/{id}")
public class InheritanceAThingResourceCodegen extends AbstractResource<InheritanceAThingBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public InheritanceAThingBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<InheritanceAThingBinding>() {
      public InheritanceAThingBinding go() {
        return toBinding(InheritanceAThing.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final InheritanceAThingBinding inheritanceAThing) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(inheritanceAThing, InheritanceAThing.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          InheritanceAThing.queries.delete(InheritanceAThing.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
