package features.rs.resources;

import features.Registry;
import features.domain.ParentD;
import features.rs.binding.ParentDBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/parentDs/{id}")
public class ParentDResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public ParentDBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ParentDBinding>() {
      public ParentDBinding go() {
        return BindingMapper.toBinding(ParentD.queries.find(id));
      }
    });
  }

}
