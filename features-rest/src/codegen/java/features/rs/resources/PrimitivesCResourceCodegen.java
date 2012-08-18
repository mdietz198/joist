package features.rs.resources;

import features.Registry;
import features.domain.PrimitivesC;
import features.rs.binding.PrimitivesCBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/primitivesCs/{id}")
public abstract class PrimitivesCResourceCodegen {

  protected final Repository repository;

  public PrimitivesCBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<PrimitivesCBinding>() {
      public PrimitivesCBinding go() {
        return BindingMapper.toBinding(PrimitivesC.queries.find(id));
      }
    });
  }

}
