package features.rs.resources;

import features.domain.ParentDChildB;
import features.rs.binding.ParentDChildBBinding;
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
import static features.rs.mappers.ParentDChildBBindingMapper.toBinding;
import static features.rs.mappers.ParentDChildBBindingMapper.toDomain;

@Path("/parentDChildBs/{id}")
public class ParentDChildBResourceCodegen extends AbstractResource<ParentDChildBBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public ParentDChildBBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ParentDChildBBinding>() {
      public ParentDChildBBinding go() {
        return toBinding(ParentDChildB.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ParentDChildBBinding parentDChildB) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(parentDChildB, ParentDChildB.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          ParentDChildB.queries.delete(ParentDChildB.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
