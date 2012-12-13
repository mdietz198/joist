package features.rs.resources;

import features.domain.ManyToManyAFoo;
import features.rs.binding.ManyToManyAFooBinding;
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
import static features.rs.mappers.ManyToManyAFooBindingMapper.toBinding;
import static features.rs.mappers.ManyToManyAFooBindingMapper.toDomain;

@Path("/manyToManyAFoos/{id}")
public class ManyToManyAFooResourceCodegen extends AbstractResource<ManyToManyAFooBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public ManyToManyAFooBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ManyToManyAFooBinding>() {
      public ManyToManyAFooBinding go() {
        return toBinding(ManyToManyAFoo.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ManyToManyAFooBinding manyToManyAFoo) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(manyToManyAFoo, ManyToManyAFoo.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          ManyToManyAFoo.queries.delete(ManyToManyAFoo.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
