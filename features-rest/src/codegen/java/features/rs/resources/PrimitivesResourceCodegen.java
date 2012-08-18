package features.rs.resources;

import features.Registry;
import features.domain.Primitives;
import features.rs.binding.PrimitivesBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/primitivess/{id}")
public class PrimitivesResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public PrimitivesBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<PrimitivesBinding>() {
      public PrimitivesBinding go() {
        return BindingMapper.toBinding(Primitives.queries.find(id));
      }
    });
  }

}
