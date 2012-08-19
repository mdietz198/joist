package features.rs.resources;

import features.Registry;
import features.domain.GrandChild;
import features.rs.binding.GrandChildBinding;
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

@Path("/grandChilds/{id}")
public class GrandChildResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public GrandChildBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<GrandChildBinding>() {
      public GrandChildBinding go() {
        return BindingMapper.toBinding(GrandChild.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @PathParam("id") Long id, final GrandChildBinding grandChild) {
    UoW.go(Registry.getRepository(), null, new Block() {
      public void go() {
        BindingMapper.toDomain(grandChild, GrandChild.queries.find(id));
      }
    });
  }

}
