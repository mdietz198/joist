package features.rs.resources;

import features.Registry;
import features.domain.InheritanceAThing;
import features.rs.binding.InheritanceAThingBinding;
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

@Path("/inheritanceAThings/{id}")
public class InheritanceAThingResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public InheritanceAThingBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<InheritanceAThingBinding>() {
      public InheritanceAThingBinding go() {
        return BindingMapper.toBinding(InheritanceAThing.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @PathParam("id") Long id, final InheritanceAThingBinding inheritanceAThing) {
    UoW.go(Registry.getRepository(), null, new Block() {
      public void go() {
        BindingMapper.toDomain(inheritanceAThing, InheritanceAThing.queries.find(id));
      }
    });
  }

}
