package features.rs.resources;

import features.domain.PrimitivesB;
import features.rs.binding.PrimitivesBBinding;
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
import static features.rs.mappers.PrimitivesBBindingMapper.toBinding;
import static features.rs.mappers.PrimitivesBBindingMapper.toDomain;

@Path("/primitivesBs/{id}")
public class PrimitivesBResourceCodegen extends AbstractResource<PrimitivesBBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public PrimitivesBBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<PrimitivesBBinding>() {
      public PrimitivesBBinding go() {
        return toBinding(PrimitivesB.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final PrimitivesBBinding primitivesB) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(primitivesB, PrimitivesB.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          PrimitivesB.queries.delete(PrimitivesB.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
