package features.rs.resources;

import features.domain.ManyToManyABar;
import features.rs.binding.ManyToManyABarBinding;
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
import static features.rs.mappers.ManyToManyABarBindingMapper.toBinding;
import static features.rs.mappers.ManyToManyABarBindingMapper.toDomain;

@Path("/manyToManyABars/{id}")
public class ManyToManyABarResourceCodegen extends AbstractResource<ManyToManyABarBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public ManyToManyABarBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ManyToManyABarBinding>() {
      public ManyToManyABarBinding go() {
        return toBinding(ManyToManyABar.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ManyToManyABarBinding manyToManyABar) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(manyToManyABar, ManyToManyABar.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          ManyToManyABar.queries.delete(ManyToManyABar.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
