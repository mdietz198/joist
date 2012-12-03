package features.rs.resources;

import features.domain.OneToOneBFoo;
import features.domain.OneToOneBFooAlias;
import features.rs.binding.OneToOneBFooBinding;
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

@Path("/oneToOneBFoos")
public class OneToOneBFooResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("name") String name) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        OneToOneBFooAlias otobf0 = new OneToOneBFooAlias();
        Select<OneToOneBFoo> q = Select.from(otobf0);
        if(name != null) {
          q.where(otobf0.name.eq(name));
        }
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final OneToOneBFooBinding oneToOneBFoo) {
    return UoW.go(repo, null, new BlockWithReturn<OneToOneBFoo>() {
      public OneToOneBFoo go() {
        OneToOneBFoo domainObject = new OneToOneBFoo();
        BindingMapper.toDomain(oneToOneBFoo, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
