package features.rs.resources;

import features.domain.Parent;
import features.rs.binding.ParentBinding;
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

@Path("/parents/{id}")
public class ParentResourceCodegen {

  @GET
  @Produces({ "application/xml" })
  public ParentBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ParentBinding>() {
      public ParentBinding go() {
        return BindingMapper.toBinding(Parent.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ParentBinding parent) {
    UoW.go(repo, null, new Block() {
      public void go() {
        BindingMapper.toDomain(parent, Parent.queries.find(id));
      }
    });
  }

}
