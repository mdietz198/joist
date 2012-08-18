package features.rs.resources;

import features.Registry;
import features.domain.InheritanceABase;
import features.rs.binding.InheritanceABaseBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/inheritanceABases/{id}")
public abstract class InheritanceABaseResourceCodegen {

  protected final Repository repository;

  public InheritanceABaseBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<InheritanceABaseBinding>() {
      public InheritanceABaseBinding go() {
        return BindingMapper.toBinding(InheritanceABase.queries.find(id));
      }
    });
  }

}
