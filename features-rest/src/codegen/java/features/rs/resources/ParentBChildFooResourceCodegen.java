package features.rs.resources;

import features.Registry;
import features.domain.ParentBChildFoo;
import features.rs.binding.ParentBChildFooBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/parentBChildFoos/{id}")
public class ParentBChildFooResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public ParentBChildFooBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ParentBChildFooBinding>() {
      public ParentBChildFooBinding go() {
        return BindingMapper.toBinding(ParentBChildFoo.queries.find(id));
      }
    });
  }

}
