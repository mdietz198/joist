package features.rs.resources;

import features.Registry;
import features.domain.ManyToManyAFooToBar;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/manyToManyAFooToBars/{id}")
public abstract class ManyToManyAFooToBarResourceCodegen {

  protected final Repository repository;

  public Object get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<Object>() {
      public Object go() {
        return BindingMapper.toDto(ManyToManyAFooToBar.queries.find(id));
      }
    });
  }

}
