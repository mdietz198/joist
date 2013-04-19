package features.rs.resources;

import features.domain.ChildIB;
import features.rs.binding.ChildIBBinding;
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
import static features.rs.mappers.ChildIBBindingMapper.toBinding;
import static features.rs.mappers.ChildIBBindingMapper.toDomain;

@Path("/childIBs/{id}")
public class ChildIBResourceCodegen extends AbstractResource<ChildIBBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public ChildIBBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ChildIBBinding>() {
      public ChildIBBinding go() {
        return toBinding(ChildIB.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ChildIBBinding childIB) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(childIB, ChildIB.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          ChildIB.queries.delete(ChildIB.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
