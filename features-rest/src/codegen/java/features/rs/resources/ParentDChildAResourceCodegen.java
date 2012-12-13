package features.rs.resources;

import features.domain.ParentDChildA;
import features.rs.binding.ParentDChildABinding;
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
import static features.rs.mappers.ParentDChildABindingMapper.toBinding;
import static features.rs.mappers.ParentDChildABindingMapper.toDomain;

@Path("/parentDChildAs/{id}")
public class ParentDChildAResourceCodegen extends AbstractResource<ParentDChildABinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public ParentDChildABinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ParentDChildABinding>() {
      public ParentDChildABinding go() {
        return toBinding(ParentDChildA.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ParentDChildABinding parentDChildA) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(parentDChildA, ParentDChildA.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          ParentDChildA.queries.delete(ParentDChildA.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
