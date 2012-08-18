package features.rs.resources;

import features.Registry;
import features.domain.OneToOneBBar;
import features.rs.binding.OneToOneBBarBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/oneToOneBBars/{id}")
public abstract class OneToOneBBarResourceCodegen {

  protected final Repository repository;

  public OneToOneBBarBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<OneToOneBBarBinding>() {
      public OneToOneBBarBinding go() {
        return BindingMapper.toBinding(OneToOneBBar.queries.find(id));
      }
    });
  }

}
