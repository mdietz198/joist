package features.rs.resources;

import features.domain.ChildF;
import features.rs.binding.ChildFBinding;
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

@Path("/childFs/{id}")
public class ChildFResourceCodegen {

  @GET
  @Produces({ "application/xml" })
  public ChildFBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ChildFBinding>() {
      public ChildFBinding go() {
        return BindingMapper.toBinding(ChildF.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ChildFBinding childF) {
    UoW.go(repo, null, new Block() {
      public void go() {
        BindingMapper.toDomain(childF, ChildF.queries.find(id));
      }
    });
  }

}