package features.rs.resources;

import features.domain.ParentBChildFoo;
import features.rs.binding.ParentBChildFooBinding;
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

@Path("/parentBChildFoos/{id}")
public class ParentBChildFooResourceCodegen {

  @GET
  @Produces({ "application/xml" })
  public ParentBChildFooBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ParentBChildFooBinding>() {
      public ParentBChildFooBinding go() {
        return BindingMapper.toBinding(ParentBChildFoo.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ParentBChildFooBinding parentBChildFoo) {
    UoW.go(repo, null, new Block() {
      public void go() {
        BindingMapper.toDomain(parentBChildFoo, ParentBChildFoo.queries.find(id));
      }
    });
  }

}
