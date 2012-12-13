package features.rs.resources;

import features.domain.ParentBChildFoo;
import features.rs.binding.ParentBChildFooBinding;
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
import static features.rs.mappers.ParentBChildFooBindingMapper.toBinding;
import static features.rs.mappers.ParentBChildFooBindingMapper.toDomain;

@Path("/parentBChildFoos/{id}")
public class ParentBChildFooResourceCodegen extends AbstractResource<ParentBChildFooBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public ParentBChildFooBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ParentBChildFooBinding>() {
      public ParentBChildFooBinding go() {
        return toBinding(ParentBChildFoo.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ParentBChildFooBinding parentBChildFoo) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(parentBChildFoo, ParentBChildFoo.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          ParentBChildFoo.queries.delete(ParentBChildFoo.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
