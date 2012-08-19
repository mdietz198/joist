package features.rs.resources;

import features.domain.ParentCFoo;
import features.rs.binding.ParentCFooBinding;
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

@Path("/parentCFoos/{id}")
public class ParentCFooResourceCodegen {

  @GET
  @Produces({ "application/xml" })
  public ParentCFooBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ParentCFooBinding>() {
      public ParentCFooBinding go() {
        return BindingMapper.toBinding(ParentCFoo.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ParentCFooBinding parentCFoo) {
    UoW.go(repo, null, new Block() {
      public void go() {
        BindingMapper.toDomain(parentCFoo, ParentCFoo.queries.find(id));
      }
    });
  }

}
