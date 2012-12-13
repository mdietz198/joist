package features.rs.resources;

import features.domain.ParentCBar;
import features.rs.binding.ParentCBarBinding;
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
import static features.rs.mappers.ParentCBarBindingMapper.toBinding;
import static features.rs.mappers.ParentCBarBindingMapper.toDomain;

@Path("/parentCBars/{id}")
public class ParentCBarResourceCodegen extends AbstractResource<ParentCBarBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public ParentCBarBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ParentCBarBinding>() {
      public ParentCBarBinding go() {
        return toBinding(ParentCBar.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ParentCBarBinding parentCBar) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(parentCBar, ParentCBar.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          ParentCBar.queries.delete(ParentCBar.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
