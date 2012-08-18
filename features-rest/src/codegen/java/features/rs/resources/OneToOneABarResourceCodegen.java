package features.rs.resources;

import features.Registry;
import features.domain.OneToOneABar;
import features.rs.binding.OneToOneABarBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/oneToOneABars/{id}")
public class OneToOneABarResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public OneToOneABarBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<OneToOneABarBinding>() {
      public OneToOneABarBinding go() {
        return BindingMapper.toBinding(OneToOneABar.queries.find(id));
      }
    });
  }

}