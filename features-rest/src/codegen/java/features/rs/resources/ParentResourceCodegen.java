package features.rs.resources;

import features.Registry;
import features.domain.Parent;
import features.rs.binding.ParentBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/parents/{id}")
public class ParentResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public ParentBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ParentBinding>() {
      public ParentBinding go() {
        return BindingMapper.toBinding(Parent.queries.find(id));
      }
    });
  }

}