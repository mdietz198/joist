package features.rs.resources;

import features.Registry;
import features.domain.ParentBChildBar;
import features.rs.binding.ParentBChildBarBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/parentBChildBars/{id}")
public class ParentBChildBarResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public ParentBChildBarBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ParentBChildBarBinding>() {
      public ParentBChildBarBinding go() {
        return BindingMapper.toBinding(ParentBChildBar.queries.find(id));
      }
    });
  }

}
