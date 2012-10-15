package features.rs.resources;

import features.domain.UserTypesAFoo;
import features.rs.binding.UserTypesAFooBinding;
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

@Path("/userTypesAFoos")
public class UserTypesAFooResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, UserTypesAFoo.class, UserTypesAFoo.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final UserTypesAFooBinding userTypesAFoo) {
    return UoW.go(repo, null, new BlockWithReturn<UserTypesAFoo>() {
      public UserTypesAFoo go() {
        UserTypesAFoo domainObject = new UserTypesAFoo();
        BindingMapper.toDomain(userTypesAFoo, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
