package features.rs.resources;

import features.Registry;
import features.domain.UserTypesAFoo;
import features.rs.binding.UserTypesAFooBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/userTypesAFoos/{id}")
public class UserTypesAFooResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public UserTypesAFooBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<UserTypesAFooBinding>() {
      public UserTypesAFooBinding go() {
        return BindingMapper.toBinding(UserTypesAFoo.queries.find(id));
      }
    });
  }

}
