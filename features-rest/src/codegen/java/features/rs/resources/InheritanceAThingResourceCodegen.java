package features.rs.resources;

import features.Registry;
import features.domain.InheritanceAThing;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Path;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/inheritanceAThings/{id}")
public abstract class InheritanceAThingResourceCodegen {

  protected final Repository repository;

  public void get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<Object>() {
      public Object go() {
        return BindingMapper.toDto(Object.queries.find(id))
      }
    });
  }

}
