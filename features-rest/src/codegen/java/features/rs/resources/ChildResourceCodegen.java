package features.rs.resources;

import features.Registry;
import features.domain.Child;
import features.rs.binding.ChildBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/childs/{id}")
public abstract class ChildResourceCodegen {

  protected final Repository repository;

  public ChildBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ChildBinding>() {
      public ChildBinding go() {
        return BindingMapper.toBinding(Child.queries.find(id));
      }
    });
  }

}
