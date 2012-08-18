package features.rs.resources;

import features.Registry;
import features.domain.ParentDToChildC;
import features.rs.binding.ParentDToChildCBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/parentDToChildCs/{id}")
public class ParentDToChildCResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public ParentDToChildCBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ParentDToChildCBinding>() {
      public ParentDToChildCBinding go() {
        return BindingMapper.toBinding(ParentDToChildC.queries.find(id));
      }
    });
  }

}