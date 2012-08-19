package features.rs.resources;

import features.domain.ManyToManyBFoo;
import features.rs.binding.ManyToManyBFooBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import joist.domain.orm.Repository;
import joist.domain.uow.Block;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/manyToManyBFoos/{id}")
public class ManyToManyBFooResourceCodegen {

  @GET
  @Produces({ "application/xml" })
  public ManyToManyBFooBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ManyToManyBFooBinding>() {
      public ManyToManyBFooBinding go() {
        return BindingMapper.toBinding(ManyToManyBFoo.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ManyToManyBFooBinding manyToManyBFoo) {
    UoW.go(repo, null, new Block() {
      public void go() {
        BindingMapper.toDomain(manyToManyBFoo, ManyToManyBFoo.queries.find(id));
      }
    });
  }

}
