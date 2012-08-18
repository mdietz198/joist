package features.rs.resources;

import features.Registry;
import features.domain.ParentBParent;
import features.rs.binding.ParentBParentBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/parentBParents/{id}")
public class ParentBParentResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public ParentBParentBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ParentBParentBinding>() {
      public ParentBParentBinding go() {
        return BindingMapper.toBinding(ParentBParent.queries.find(id));
      }
    });
  }

}
