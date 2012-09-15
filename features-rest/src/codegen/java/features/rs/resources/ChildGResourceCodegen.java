package features.rs.resources;

import features.domain.ChildG;
import features.rs.binding.ChildGBinding;
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

@Path("/childGs/{id}")
public class ChildGResourceCodegen {

  @GET
  @Produces({ "application/xml" })
  public ChildGBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ChildGBinding>() {
      public ChildGBinding go() {
        return BindingMapper.toBinding(ChildG.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ChildGBinding childG) {
    UoW.go(repo, null, new Block() {
      public void go() {
        BindingMapper.toDomain(childG, ChildG.queries.find(id));
      }
    });
  }

}