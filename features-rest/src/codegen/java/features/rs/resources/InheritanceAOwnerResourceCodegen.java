package features.rs.resources;

import features.Registry;
import features.domain.InheritanceAOwner;
import features.rs.binding.InheritanceAOwnerBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/inheritanceAOwners/{id}")
public abstract class InheritanceAOwnerResourceCodegen {

  protected final Repository repository;

  public InheritanceAOwnerBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<InheritanceAOwnerBinding>() {
      public InheritanceAOwnerBinding go() {
        return BindingMapper.toBinding(InheritanceAOwner.queries.find(id));
      }
    });
  }

}
