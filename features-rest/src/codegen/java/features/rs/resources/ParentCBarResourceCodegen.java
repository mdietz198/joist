package features.rs.resources;

import features.Registry;
import features.domain.ParentCBar;
import features.rs.binding.ParentCBarBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/parentCBars/{id}")
public class ParentCBarResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public ParentCBarBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ParentCBarBinding>() {
      public ParentCBarBinding go() {
        return BindingMapper.toBinding(ParentCBar.queries.find(id));
      }
    });
  }

}