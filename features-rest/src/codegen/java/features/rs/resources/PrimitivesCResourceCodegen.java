package features.rs.resources;

import features.domain.PrimitivesC;
import features.rs.binding.PrimitivesCBinding;
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

@Path("/primitivesCs/{id}")
public class PrimitivesCResourceCodegen {

  @GET
  @Produces({ "application/xml" })
  public PrimitivesCBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<PrimitivesCBinding>() {
      public PrimitivesCBinding go() {
        return BindingMapper.toBinding(PrimitivesC.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final PrimitivesCBinding primitivesC) {
    UoW.go(repo, null, new Block() {
      public void go() {
        BindingMapper.toDomain(primitivesC, PrimitivesC.queries.find(id));
      }
    });
  }

}
