package features.rs.resources;

import features.Registry;
import features.domain.OneToOneBFoo;
import features.rs.binding.OneToOneBFooBinding;
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

@Path("/oneToOneBFoos/{id}")
public class OneToOneBFooResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public OneToOneBFooBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<OneToOneBFooBinding>() {
      public OneToOneBFooBinding go() {
        return BindingMapper.toBinding(OneToOneBFoo.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @PathParam("id") Long id, final OneToOneBFooBinding oneToOneBFoo) {
    UoW.go(Registry.getRepository(), null, new Block() {
      public void go() {
        BindingMapper.toDomain(oneToOneBFoo, OneToOneBFoo.queries.find(id));
      }
    });
  }

}
