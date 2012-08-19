package features.rs.resources;

import features.domain.ManyToManyAFoo;
import features.rs.binding.ManyToManyAFooBinding;
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

@Path("/manyToManyAFoos/{id}")
public class ManyToManyAFooResourceCodegen {

  @GET
  @Produces({ "application/xml" })
  public ManyToManyAFooBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ManyToManyAFooBinding>() {
      public ManyToManyAFooBinding go() {
        return BindingMapper.toBinding(ManyToManyAFoo.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ManyToManyAFooBinding manyToManyAFoo) {
    UoW.go(repo, null, new Block() {
      public void go() {
        BindingMapper.toDomain(manyToManyAFoo, ManyToManyAFoo.queries.find(id));
      }
    });
  }

}
