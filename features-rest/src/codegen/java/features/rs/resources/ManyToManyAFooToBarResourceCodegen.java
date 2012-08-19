package features.rs.resources;

import features.domain.ManyToManyAFooToBar;
import features.rs.binding.ManyToManyAFooToBarBinding;
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

@Path("/manyToManyAFooToBars/{id}")
public class ManyToManyAFooToBarResourceCodegen {

  @GET
  @Produces({ "application/xml" })
  public ManyToManyAFooToBarBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ManyToManyAFooToBarBinding>() {
      public ManyToManyAFooToBarBinding go() {
        return BindingMapper.toBinding(ManyToManyAFooToBar.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ManyToManyAFooToBarBinding manyToManyAFooToBar) {
    UoW.go(repo, null, new Block() {
      public void go() {
        BindingMapper.toDomain(manyToManyAFooToBar, ManyToManyAFooToBar.queries.find(id));
      }
    });
  }

}
