package features.rs.resources;

import features.domain.ParentBChildBar;
import features.rs.binding.ParentBChildBarBinding;
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
import static features.rs.mappers.ParentBChildBarBindingMapper.toBinding;
import static features.rs.mappers.ParentBChildBarBindingMapper.toDomain;

@Path("/parentBChildBars/{id}")
public class ParentBChildBarResourceCodegen extends AbstractResource<ParentBChildBarBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public ParentBChildBarBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ParentBChildBarBinding>() {
      public ParentBChildBarBinding go() {
        return toBinding(ParentBChildBar.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ParentBChildBarBinding parentBChildBar) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(parentBChildBar, ParentBChildBar.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          ParentBChildBar.queries.delete(ParentBChildBar.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
