package features.rs.resources;

import features.Registry;
import features.domain.InheritanceBRoot;
import features.rs.binding.InheritanceBRootBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/inheritanceBRoots/{id}")
public abstract class InheritanceBRootResourceCodegen {

  protected final Repository repository;

  public InheritanceBRootBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<InheritanceBRootBinding>() {
      public InheritanceBRootBinding go() {
        return BindingMapper.toBinding(InheritanceBRoot.queries.find(id));
      }
    });
  }

}
