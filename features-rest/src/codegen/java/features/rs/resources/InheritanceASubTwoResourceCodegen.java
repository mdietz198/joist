package features.rs.resources;

import features.Registry;
import features.domain.InheritanceASubTwo;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/inheritanceASubTwos/{id}")
public abstract class InheritanceASubTwoResourceCodegen {

  protected final Repository repository;

  public Object get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<Object>() {
      public Object go() {
        return BindingMapper.toDto(InheritanceASubTwo.queries.find(id));
      }
    });
  }

}
