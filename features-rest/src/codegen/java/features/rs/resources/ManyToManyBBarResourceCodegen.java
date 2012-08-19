package features.rs.resources;

import features.domain.ManyToManyBBar;
import features.rs.binding.ManyToManyBBarBinding;
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

@Path("/manyToManyBBars/{id}")
public class ManyToManyBBarResourceCodegen {

  @GET
  @Produces({ "application/xml" })
  public ManyToManyBBarBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ManyToManyBBarBinding>() {
      public ManyToManyBBarBinding go() {
        return BindingMapper.toBinding(ManyToManyBBar.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ManyToManyBBarBinding manyToManyBBar) {
    UoW.go(repo, null, new Block() {
      public void go() {
        BindingMapper.toDomain(manyToManyBBar, ManyToManyBBar.queries.find(id));
      }
    });
  }

}
