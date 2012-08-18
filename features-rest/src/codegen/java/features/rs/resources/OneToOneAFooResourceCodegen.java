package features.rs.resources;

import features.Registry;
import features.domain.OneToOneAFoo;
import features.rs.binding.OneToOneAFooBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/oneToOneAFoos/{id}")
public abstract class OneToOneAFooResourceCodegen {

  protected final Repository repository;

  public OneToOneAFooBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<OneToOneAFooBinding>() {
      public OneToOneAFooBinding go() {
        return BindingMapper.toBinding(OneToOneAFoo.queries.find(id));
      }
    });
  }

}
