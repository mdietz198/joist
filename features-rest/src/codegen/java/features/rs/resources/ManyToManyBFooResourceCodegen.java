package features.rs.resources;

import features.domain.ManyToManyBFoo;
import features.rs.binding.ManyToManyBFooBinding;
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
import static features.rs.mappers.ManyToManyBFooBindingMapper.toBinding;
import static features.rs.mappers.ManyToManyBFooBindingMapper.toDomain;

@Path("/manyToManyBFoos/{id}")
public class ManyToManyBFooResourceCodegen extends AbstractResource<ManyToManyBFooBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public ManyToManyBFooBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ManyToManyBFooBinding>() {
      public ManyToManyBFooBinding go() {
        return toBinding(ManyToManyBFoo.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ManyToManyBFooBinding manyToManyBFoo) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(manyToManyBFoo, ManyToManyBFoo.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          ManyToManyBFoo.queries.delete(ManyToManyBFoo.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
