package features.rs.resources;

import features.domain.GrandChild;
import features.rs.binding.GrandChildBinding;
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
import static features.rs.mappers.GrandChildBindingMapper.toBinding;
import static features.rs.mappers.GrandChildBindingMapper.toDomain;

@Path("/grandChilds/{id}")
public class GrandChildResourceCodegen extends AbstractResource<GrandChildBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public GrandChildBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<GrandChildBinding>() {
      public GrandChildBinding go() {
        return toBinding(GrandChild.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final GrandChildBinding grandChild) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(grandChild, GrandChild.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          GrandChild.queries.delete(GrandChild.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
