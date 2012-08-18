package features.rs.resources;

import features.Registry;
import features.domain.InheritanceCFoo1;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/inheritanceCFoo1s/{id}")
public abstract class InheritanceCFoo1ResourceCodegen {

  protected final Repository repository;

  public Object get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<Object>() {
      public Object go() {
        return BindingMapper.toDto(InheritanceCFoo1.queries.find(id));
      }
    });
  }

}
