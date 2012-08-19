package features.rs.resources;

import features.Registry;
import features.domain.ValuesA;
import features.rs.binding.ValuesABinding;
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

@Path("/valuesAs/{id}")
public class ValuesAResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public ValuesABinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ValuesABinding>() {
      public ValuesABinding go() {
        return BindingMapper.toBinding(ValuesA.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @PathParam("id") Long id, final ValuesABinding valuesA) {
    UoW.go(Registry.getRepository(), null, new Block() {
      public void go() {
        BindingMapper.toDomain(valuesA, ValuesA.queries.find(id));
      }
    });
  }

}
