package features.rs.resources;

import features.domain.ParentBParent;
import features.rs.binding.ParentBParentBinding;
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
import static features.rs.mappers.ParentBParentBindingMapper.toBinding;
import static features.rs.mappers.ParentBParentBindingMapper.toDomain;

@Path("/parentBParents/{id}")
public class ParentBParentResourceCodegen extends AbstractResource<ParentBParentBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public ParentBParentBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ParentBParentBinding>() {
      public ParentBParentBinding go() {
        return toBinding(ParentBParent.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ParentBParentBinding parentBParent) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(parentBParent, ParentBParent.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          ParentBParent.queries.delete(ParentBParent.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
