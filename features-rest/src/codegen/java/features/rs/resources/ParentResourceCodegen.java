package features.rs.resources;

import features.Registry;
import features.domain.Parent;
import features.rs.binding.ParentBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/parents/{id}")
public abstract class ParentResourceCodegen {

  protected final Repository repository;

  public ParentBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ParentBinding>() {
      public ParentBinding go() {
        return BindingMapper.toBinding(Parent.queries.find(id));
      }
    });
  }

}
