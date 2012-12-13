package features.rs.resources;

import features.domain.ParentE;
import features.rs.binding.ParentEBinding;
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
import static features.rs.mappers.ParentEBindingMapper.toBinding;
import static features.rs.mappers.ParentEBindingMapper.toDomain;

@Path("/parentEs/{id}")
public class ParentEResourceCodegen extends AbstractResource<ParentEBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public ParentEBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ParentEBinding>() {
      public ParentEBinding go() {
        return toBinding(ParentE.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ParentEBinding parentE) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(parentE, ParentE.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          ParentE.queries.delete(ParentE.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
