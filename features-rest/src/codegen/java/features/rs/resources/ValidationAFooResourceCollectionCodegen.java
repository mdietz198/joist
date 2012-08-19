package features.rs.resources;

import features.domain.ValidationAFoo;
import features.rs.binding.ValidationAFooBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.LinkCollection;

@Path("/validationAFoos")
public class ValidationAFooResourceCollectionCodegen {

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get(final @Context Repository repo) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, ValidationAFoo.class, ValidationAFoo.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final @Context Repository repo, final ValidationAFooBinding validationAFoo) {
    return UoW.go(repo, null, new BlockWithReturn<ValidationAFoo>() {
      public ValidationAFoo go() {
        ValidationAFoo domainObject = new ValidationAFoo();
        BindingMapper.toDomain(validationAFoo, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
