package features.rs.resources;

import features.domain.ManyToManyBBar;
import features.rs.binding.ManyToManyBBarBinding;
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
import static features.rs.mappers.ManyToManyBBarBindingMapper.toBinding;
import static features.rs.mappers.ManyToManyBBarBindingMapper.toDomain;

@Path("/manyToManyBBars/{id}")
public class ManyToManyBBarResourceCodegen extends AbstractResource<ManyToManyBBarBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public ManyToManyBBarBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ManyToManyBBarBinding>() {
      public ManyToManyBBarBinding go() {
        return toBinding(ManyToManyBBar.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ManyToManyBBarBinding manyToManyBBar) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(manyToManyBBar, ManyToManyBBar.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          ManyToManyBBar.queries.delete(ManyToManyBBar.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
