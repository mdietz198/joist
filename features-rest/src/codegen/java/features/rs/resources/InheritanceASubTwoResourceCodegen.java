package features.rs.resources;

import features.Registry;
import features.domain.InheritanceASubTwo;
import features.rs.binding.InheritanceASubTwoBinding;
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

@Path("/inheritanceASubTwos/{id}")
public class InheritanceASubTwoResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public InheritanceASubTwoBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<InheritanceASubTwoBinding>() {
      public InheritanceASubTwoBinding go() {
        return BindingMapper.toBinding(InheritanceASubTwo.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @PathParam("id") Long id, final InheritanceASubTwoBinding inheritanceASubTwo) {
    UoW.go(Registry.getRepository(), null, new Block() {
      public void go() {
        BindingMapper.toDomain(inheritanceASubTwo, InheritanceASubTwo.queries.find(id));
      }
    });
  }

}
