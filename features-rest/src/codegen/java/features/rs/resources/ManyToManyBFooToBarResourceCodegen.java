package features.rs.resources;

import features.Registry;
import features.domain.ManyToManyBFooToBar;
import features.rs.binding.ManyToManyBFooToBarBinding;
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

@Path("/manyToManyBFooToBars/{id}")
public class ManyToManyBFooToBarResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public ManyToManyBFooToBarBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ManyToManyBFooToBarBinding>() {
      public ManyToManyBFooToBarBinding go() {
        return BindingMapper.toBinding(ManyToManyBFooToBar.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @PathParam("id") Long id, final ManyToManyBFooToBarBinding manyToManyBFooToBar) {
    UoW.go(Registry.getRepository(), null, new Block() {
      public void go() {
        BindingMapper.toDomain(manyToManyBFooToBar, ManyToManyBFooToBar.queries.find(id));
      }
    });
  }

}
