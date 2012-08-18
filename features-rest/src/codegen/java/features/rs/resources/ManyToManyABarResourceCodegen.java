package features.rs.resources;

import features.Registry;
import features.domain.ManyToManyABar;
import features.rs.binding.ManyToManyABarBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/manyToManyABars/{id}")
public class ManyToManyABarResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public ManyToManyABarBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ManyToManyABarBinding>() {
      public ManyToManyABarBinding go() {
        return BindingMapper.toBinding(ManyToManyABar.queries.find(id));
      }
    });
  }

}
