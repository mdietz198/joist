package features.rs.resources;

import features.Registry;
import features.domain.ParentDChildC;
import features.rs.binding.ParentDChildCBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/parentDChildCs/{id}")
public class ParentDChildCResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public ParentDChildCBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ParentDChildCBinding>() {
      public ParentDChildCBinding go() {
        return BindingMapper.toBinding(ParentDChildC.queries.find(id));
      }
    });
  }

}
