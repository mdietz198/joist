package features.rs.resources;

import features.Registry;
import features.domain.ParentDChildB;
import features.rs.binding.ParentDChildBBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/parentDChildBs/{id}")
public class ParentDChildBResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public ParentDChildBBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ParentDChildBBinding>() {
      public ParentDChildBBinding go() {
        return BindingMapper.toBinding(ParentDChildB.queries.find(id));
      }
    });
  }

}
