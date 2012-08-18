package features.rs.resources;

import features.Registry;
import features.domain.InheritanceAOwner;
import features.rs.binding.InheritanceAOwnerBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/inheritanceAOwners/{id}")
public class InheritanceAOwnerResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public InheritanceAOwnerBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<InheritanceAOwnerBinding>() {
      public InheritanceAOwnerBinding go() {
        return BindingMapper.toBinding(InheritanceAOwner.queries.find(id));
      }
    });
  }

}
