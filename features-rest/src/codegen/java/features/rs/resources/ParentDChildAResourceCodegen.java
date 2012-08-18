package features.rs.resources;

import features.Registry;
import features.domain.ParentDChildA;
import features.rs.binding.ParentDChildABinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/parentDChildAs/{id}")
public class ParentDChildAResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public ParentDChildABinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ParentDChildABinding>() {
      public ParentDChildABinding go() {
        return BindingMapper.toBinding(ParentDChildA.queries.find(id));
      }
    });
  }

}
