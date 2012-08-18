package features.rs.resources;

import features.Registry;
import features.domain.ManyToManyAFooToBar;
import features.rs.binding.ManyToManyAFooToBarBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/manyToManyAFooToBars/{id}")
public abstract class ManyToManyAFooToBarResourceCodegen {

  protected final Repository repository;

  public ManyToManyAFooToBarBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ManyToManyAFooToBarBinding>() {
      public ManyToManyAFooToBarBinding go() {
        return BindingMapper.toBinding(ManyToManyAFooToBar.queries.find(id));
      }
    });
  }

}
