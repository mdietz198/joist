package features.rs.resources;

import features.domain.ValidationAFoo;
import features.rs.binding.ValidationAFooBinding;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import joist.domain.exceptions.NotFoundException;
import joist.domain.orm.Repository;
import joist.domain.uow.Block;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.AbstractResource;
import static features.rs.mappers.ValidationAFooBindingMapper.toBinding;
import static features.rs.mappers.ValidationAFooBindingMapper.toDomain;

@Path("/validationAFoos/{id}")
public class ValidationAFooResourceCodegen extends AbstractResource<ValidationAFooBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public ValidationAFooBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ValidationAFooBinding>() {
      public ValidationAFooBinding go() {
        return toBinding(ValidationAFoo.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ValidationAFooBinding validationAFoo) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(validationAFoo, ValidationAFoo.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          ValidationAFoo.queries.delete(ValidationAFoo.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
