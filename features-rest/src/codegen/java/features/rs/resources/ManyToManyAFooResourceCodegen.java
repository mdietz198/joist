package features.rs.resources;

import features.Registry;
import features.domain.ManyToManyAFoo;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/manyToManyAFoos/{id}")
public abstract class ManyToManyAFooResourceCodegen {

  protected final Repository repository;

  public Object get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<Object>() {
      public Object go() {
        return BindingMapper.toDto(ManyToManyAFoo.queries.find(id));
      }
    });
  }

}
