package features.rs.resources;

import features.domain.ValuesA;
import features.rs.binding.ValuesABinding;
import features.rs.helpers.BindingMapper;
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

@Path("/valuesAs/{id}")
public class ValuesAResourceCodegen {

  @GET
  @Produces({ "application/xml" })
  public ValuesABinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ValuesABinding>() {
      public ValuesABinding go() {
        return BindingMapper.toBinding(ValuesA.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ValuesABinding valuesA) {
    UoW.go(repo, null, new Block() {
      public void go() {
        BindingMapper.toDomain(valuesA, ValuesA.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          ValuesA.queries.delete(ValuesA.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotentA
        }
      }
    });
  }

}
