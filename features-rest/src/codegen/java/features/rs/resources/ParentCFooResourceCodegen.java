package features.rs.resources;

import features.Registry;
import features.domain.ParentCFoo;
import features.rs.binding.ParentCFooBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/parentCFoos/{id}")
public abstract class ParentCFooResourceCodegen {

  protected final Repository repository;

  public ParentCFooBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ParentCFooBinding>() {
      public ParentCFooBinding go() {
        return BindingMapper.toBinding(ParentCFoo.queries.find(id));
      }
    });
  }

}
