package features.rs.resources;

import features.Registry;
import features.domain.OneToOneBFoo;
import features.rs.binding.OneToOneBFooBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/oneToOneBFoos/{id}")
public abstract class OneToOneBFooResourceCodegen {

  protected final Repository repository;

  public OneToOneBFooBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<OneToOneBFooBinding>() {
      public OneToOneBFooBinding go() {
        return BindingMapper.toBinding(OneToOneBFoo.queries.find(id));
      }
    });
  }

}
