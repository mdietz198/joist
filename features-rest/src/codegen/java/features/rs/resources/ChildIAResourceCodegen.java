package features.rs.resources;

import features.domain.ChildIA;
import features.rs.binding.ChildIABinding;
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
import static features.rs.mappers.ChildIABindingMapper.toBinding;
import static features.rs.mappers.ChildIABindingMapper.toDomain;

@Path("/childIAs/{id}")
public class ChildIAResourceCodegen extends AbstractResource<ChildIABinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public ChildIABinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ChildIABinding>() {
      public ChildIABinding go() {
        return toBinding(ChildIA.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ChildIABinding childIA) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(childIA, ChildIA.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          ChildIA.queries.delete(ChildIA.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
