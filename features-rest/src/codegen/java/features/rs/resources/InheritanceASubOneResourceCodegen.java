package features.rs.resources;

import features.Registry;
import features.domain.InheritanceASubOne;
import features.rs.binding.InheritanceASubOneBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/inheritanceASubOnes/{id}")
public class InheritanceASubOneResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public InheritanceASubOneBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<InheritanceASubOneBinding>() {
      public InheritanceASubOneBinding go() {
        return BindingMapper.toBinding(InheritanceASubOne.queries.find(id));
      }
    });
  }

}
