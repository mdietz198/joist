package features.rs.resources;

import features.Registry;
import features.domain.ManyToManyAFoo;
import features.rs.binding.ManyToManyAFooBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/manyToManyAFoos/{id}")
public class ManyToManyAFooResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public ManyToManyAFooBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ManyToManyAFooBinding>() {
      public ManyToManyAFooBinding go() {
        return BindingMapper.toBinding(ManyToManyAFoo.queries.find(id));
      }
    });
  }

}
