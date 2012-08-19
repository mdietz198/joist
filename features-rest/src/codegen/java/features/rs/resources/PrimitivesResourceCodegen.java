package features.rs.resources;

import features.Registry;
import features.domain.Primitives;
import features.rs.binding.PrimitivesBinding;
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

@Path("/primitivess/{id}")
public class PrimitivesResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public PrimitivesBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<PrimitivesBinding>() {
      public PrimitivesBinding go() {
        return BindingMapper.toBinding(Primitives.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @PathParam("id") Long id, final PrimitivesBinding primitives) {
    UoW.go(Registry.getRepository(), null, new Block() {
      public void go() {
        BindingMapper.toDomain(primitives, Primitives.queries.find(id));
      }
    });
  }

}
