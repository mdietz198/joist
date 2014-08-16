package features.rs.resources;

import features.domain.ParentBChildZaz;
import features.rs.binding.ParentBChildZazBinding;
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
import static features.rs.mappers.ParentBChildZazBindingMapper.toBinding;
import static features.rs.mappers.ParentBChildZazBindingMapper.toDomain;

@Path("/parentBChildZazs/{id}")
public class ParentBChildZazResourceCodegen extends AbstractResource<ParentBChildZazBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public ParentBChildZazBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ParentBChildZazBinding>() {
      public ParentBChildZazBinding go() {
        return toBinding(ParentBChildZaz.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ParentBChildZazBinding parentBChildZaz) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(parentBChildZaz, ParentBChildZaz.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          ParentBChildZaz.queries.delete(ParentBChildZaz.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
