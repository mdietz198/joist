package features.rs.resources;

import features.domain.ParentF;
import features.rs.binding.ParentFBinding;
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
import static features.rs.mappers.ParentFBindingMapper.toBinding;
import static features.rs.mappers.ParentFBindingMapper.toDomain;

@Path("/parentFs/{id}")
public class ParentFResourceCodegen extends AbstractResource<ParentFBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public ParentFBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ParentFBinding>() {
      public ParentFBinding go() {
        return toBinding(ParentF.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ParentFBinding parentF) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(parentF, ParentF.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          ParentF.queries.delete(ParentF.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
