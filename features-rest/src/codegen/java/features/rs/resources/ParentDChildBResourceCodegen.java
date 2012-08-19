package features.rs.resources;

import features.domain.ParentDChildB;
import features.rs.binding.ParentDChildBBinding;
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

@Path("/parentDChildBs/{id}")
public class ParentDChildBResourceCodegen {

  @GET
  @Produces({ "application/xml" })
  public ParentDChildBBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ParentDChildBBinding>() {
      public ParentDChildBBinding go() {
        return BindingMapper.toBinding(ParentDChildB.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ParentDChildBBinding parentDChildB) {
    UoW.go(repo, null, new Block() {
      public void go() {
        BindingMapper.toDomain(parentDChildB, ParentDChildB.queries.find(id));
      }
    });
  }

}
