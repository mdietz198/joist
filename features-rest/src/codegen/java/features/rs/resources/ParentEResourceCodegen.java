package features.rs.resources;

import features.Registry;
import features.domain.ParentE;
import features.rs.binding.ParentEBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/parentEs/{id}")
public class ParentEResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public ParentEBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ParentEBinding>() {
      public ParentEBinding go() {
        return BindingMapper.toBinding(ParentE.queries.find(id));
      }
    });
  }

}
