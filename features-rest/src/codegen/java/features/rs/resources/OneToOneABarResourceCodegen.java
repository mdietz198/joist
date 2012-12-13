package features.rs.resources;

import features.domain.OneToOneABar;
import features.rs.binding.OneToOneABarBinding;
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
import static features.rs.mappers.OneToOneABarBindingMapper.toBinding;
import static features.rs.mappers.OneToOneABarBindingMapper.toDomain;

@Path("/oneToOneABars/{id}")
public class OneToOneABarResourceCodegen extends AbstractResource<OneToOneABarBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public OneToOneABarBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<OneToOneABarBinding>() {
      public OneToOneABarBinding go() {
        return toBinding(OneToOneABar.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final OneToOneABarBinding oneToOneABar) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(oneToOneABar, OneToOneABar.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          OneToOneABar.queries.delete(OneToOneABar.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
