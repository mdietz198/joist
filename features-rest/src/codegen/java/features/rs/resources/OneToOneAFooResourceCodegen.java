package features.rs.resources;

import features.Registry;
import features.domain.OneToOneAFoo;
import features.rs.binding.OneToOneAFooBinding;
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

@Path("/oneToOneAFoos/{id}")
public class OneToOneAFooResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public OneToOneAFooBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<OneToOneAFooBinding>() {
      public OneToOneAFooBinding go() {
        return BindingMapper.toBinding(OneToOneAFoo.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @PathParam("id") Long id, final OneToOneAFooBinding oneToOneAFoo) {
    UoW.go(Registry.getRepository(), null, new Block() {
      public void go() {
        BindingMapper.toDomain(oneToOneAFoo, OneToOneAFoo.queries.find(id));
      }
    });
  }

}
