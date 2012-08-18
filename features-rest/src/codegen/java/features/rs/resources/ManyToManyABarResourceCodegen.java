package features.rs.resources;

import features.Registry;
import features.domain.ManyToManyABar;
import features.rs.binding.ManyToManyABarBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/manyToManyABars/{id}")
public abstract class ManyToManyABarResourceCodegen {

  protected final Repository repository;

  public ManyToManyABarBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ManyToManyABarBinding>() {
      public ManyToManyABarBinding go() {
        return BindingMapper.toBinding(ManyToManyABar.queries.find(id));
      }
    });
  }

}
