package features.rs.resources;

import features.Registry;
import features.domain.InheritanceAThing;
import features.rs.binding.InheritanceAThingBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
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

}