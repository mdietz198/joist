package features.rs.resources;

import features.Registry;
import features.domain.OneToOneBBar;
import features.rs.binding.OneToOneBBarBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/oneToOneBBars/{id}")
public class OneToOneBBarResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public OneToOneBBarBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<OneToOneBBarBinding>() {
      public OneToOneBBarBinding go() {
        return BindingMapper.toBinding(OneToOneBBar.queries.find(id));
      }
    });
  }

}
