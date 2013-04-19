package features.rs.resources;

import features.domain.ChildH;
import features.rs.binding.ChildHBinding;
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
import static features.rs.mappers.ChildHBindingMapper.toBinding;
import static features.rs.mappers.ChildHBindingMapper.toDomain;

@Path("/childHs/{id}")
public class ChildHResourceCodegen extends AbstractResource<ChildHBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public ChildHBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ChildHBinding>() {
      public ChildHBinding go() {
        return toBinding(ChildH.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ChildHBinding childH) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(childH, ChildH.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          ChildH.queries.delete(ChildH.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
