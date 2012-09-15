package features.rs.resources;

import features.domain.ParentG;
import features.rs.binding.ParentGBinding;
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

@Path("/parentGs/{id}")
public class ParentGResourceCodegen {

  @GET
  @Produces({ "application/xml" })
  public ParentGBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ParentGBinding>() {
      public ParentGBinding go() {
        return BindingMapper.toBinding(ParentG.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ParentGBinding parentG) {
    UoW.go(repo, null, new Block() {
      public void go() {
        BindingMapper.toDomain(parentG, ParentG.queries.find(id));
      }
    });
  }

}
