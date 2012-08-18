package features.rs.resources;

import features.Registry;
import features.domain.ManyToManyBFoo;
import features.rs.binding.ManyToManyBFooBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/manyToManyBFoos/{id}")
public class ManyToManyBFooResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public ManyToManyBFooBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ManyToManyBFooBinding>() {
      public ManyToManyBFooBinding go() {
        return BindingMapper.toBinding(ManyToManyBFoo.queries.find(id));
      }
    });
  }

}