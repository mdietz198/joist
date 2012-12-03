package features.rs.resources;

import features.domain.ValidationAFoo;
import features.domain.ValidationAFooAlias;
import features.rs.binding.ValidationAFooBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import joist.domain.orm.Repository;
import joist.domain.orm.queries.Select;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.LinkCollection;

@Path("/validationAFoos")
public class ValidationAFooResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("name") String name) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        ValidationAFooAlias vaf0 = new ValidationAFooAlias();
        Select<ValidationAFoo> q = Select.from(vaf0);
        if(name != null) {
          q.where(vaf0.name.eq(name));
        }
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
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
