package features.rs.resources;

import features.Registry;
import features.domain.ManyToManyAFooToBar;
import features.rs.binding.ManyToManyAFooToBarBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/manyToManyAFooToBars/{id}")
public class ManyToManyAFooToBarResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public ManyToManyAFooToBarBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ManyToManyAFooToBarBinding>() {
      public ManyToManyAFooToBarBinding go() {
        return BindingMapper.toBinding(ManyToManyAFooToBar.queries.find(id));
      }
    });
  }

}