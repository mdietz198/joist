package features.rs.resources;

import features.domain.ChildG;
import features.rs.binding.ChildGBinding;
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
import static features.rs.mappers.ChildGBindingMapper.toBinding;
import static features.rs.mappers.ChildGBindingMapper.toDomain;

@Path("/childGs/{id}")
public class ChildGResourceCodegen extends AbstractResource<ChildGBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public ChildGBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ChildGBinding>() {
      public ChildGBinding go() {
        return toBinding(ChildG.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ChildGBinding childG) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(childG, ChildG.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          ChildG.queries.delete(ChildG.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
