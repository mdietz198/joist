package features.rs.resources;

import features.domain.ValidationAFoo;
import features.rs.binding.ValidationAFooBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import joist.domain.orm.Repository;
import joist.domain.uow.Block;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/validationAFoos/{id}")
public class ValidationAFooResourceCodegen {

  @GET
  @Produces({ "application/xml" })
  public ValidationAFooBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ValidationAFooBinding>() {
      public ValidationAFooBinding go() {
        return BindingMapper.toBinding(ValidationAFoo.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ValidationAFooBinding validationAFoo) {
    UoW.go(repo, null, new Block() {
      public void go() {
        BindingMapper.toDomain(validationAFoo, ValidationAFoo.queries.find(id));
      }
    });
  }

}
