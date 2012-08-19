package features.rs.resources;

import features.Registry;
import features.domain.ManyToManyBBar;
import features.rs.binding.ManyToManyBBarBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.Block;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/manyToManyBBars/{id}")
public class ManyToManyBBarResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public ManyToManyBBarBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ManyToManyBBarBinding>() {
      public ManyToManyBBarBinding go() {
        return BindingMapper.toBinding(ManyToManyBBar.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @PathParam("id") Long id, final ManyToManyBBarBinding manyToManyBBar) {
    UoW.go(Registry.getRepository(), null, new Block() {
      public void go() {
        BindingMapper.toDomain(manyToManyBBar, ManyToManyBBar.queries.find(id));
      }
    });
  }

}
