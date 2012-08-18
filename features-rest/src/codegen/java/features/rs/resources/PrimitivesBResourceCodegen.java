package features.rs.resources;

import features.Registry;
import features.domain.PrimitivesB;
import features.rs.binding.PrimitivesBBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/primitivesBs/{id}")
public abstract class PrimitivesBResourceCodegen {

  protected final Repository repository;

  public PrimitivesBBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<PrimitivesBBinding>() {
      public PrimitivesBBinding go() {
        return BindingMapper.toBinding(PrimitivesB.queries.find(id));
      }
    });
  }

}
