package features.rs.resources;

import features.domain.ParentDChildC;
import features.rs.binding.ParentDChildCBinding;
import features.rs.helpers.BindingMapper;
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

@Path("/parentDChildCs/{id}")
public class ParentDChildCResourceCodegen {

  @GET
  @Produces({ "application/xml" })
  public ParentDChildCBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ParentDChildCBinding>() {
      public ParentDChildCBinding go() {
        return BindingMapper.toBinding(ParentDChildC.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ParentDChildCBinding parentDChildC) {
    UoW.go(repo, null, new Block() {
      public void go() {
        BindingMapper.toDomain(parentDChildC, ParentDChildC.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          ParentDChildC.queries.delete(ParentDChildC.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotentA
        }
      }
    });
  }

}
