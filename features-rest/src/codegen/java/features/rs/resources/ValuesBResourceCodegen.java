package features.rs.resources;

import features.Registry;
import features.domain.ValuesB;
import features.rs.binding.ValuesBBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/valuesBs/{id}")
public abstract class ValuesBResourceCodegen {

  protected final Repository repository;

  public ValuesBBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ValuesBBinding>() {
      public ValuesBBinding go() {
        return BindingMapper.toBinding(ValuesB.queries.find(id));
      }
    });
  }

}
