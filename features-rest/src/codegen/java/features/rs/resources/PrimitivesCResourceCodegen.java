package features.rs.resources;

import features.domain.PrimitivesC;
import features.rs.binding.PrimitivesCBinding;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import joist.domain.exceptions.NotFoundException;
import joist.domain.orm.Repository;
import joist.domain.uow.Block;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.AbstractResource;
import static features.rs.mappers.PrimitivesCBindingMapper.toBinding;
import static features.rs.mappers.PrimitivesCBindingMapper.toDomain;

@Path("/primitivesCs/{id}")
public class PrimitivesCResourceCodegen extends AbstractResource<PrimitivesCBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public PrimitivesCBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<PrimitivesCBinding>() {
      public PrimitivesCBinding go() {
        return toBinding(PrimitivesC.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final PrimitivesCBinding primitivesC) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(primitivesC, PrimitivesC.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          PrimitivesC.queries.delete(PrimitivesC.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
