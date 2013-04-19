package features.rs.resources;

import features.domain.ParentI;
import features.rs.binding.ParentIBinding;
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
import static features.rs.mappers.ParentIBindingMapper.toBinding;
import static features.rs.mappers.ParentIBindingMapper.toDomain;

@Path("/parentIs/{id}")
public class ParentIResourceCodegen extends AbstractResource<ParentIBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public ParentIBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ParentIBinding>() {
      public ParentIBinding go() {
        return toBinding(ParentI.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ParentIBinding parentI) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(parentI, ParentI.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          ParentI.queries.delete(ParentI.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
