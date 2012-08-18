package features.rs.resources;

import features.Registry;
import features.domain.ValidationAFoo;
import features.rs.binding.ValidationAFooBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/validationAFoos/{id}")
public class ValidationAFooResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public ValidationAFooBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ValidationAFooBinding>() {
      public ValidationAFooBinding go() {
        return BindingMapper.toBinding(ValidationAFoo.queries.find(id));
      }
    });
  }

}
