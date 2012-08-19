package features.rs.resources;

import features.domain.OneToOneAFoo;
import features.rs.binding.OneToOneAFooBinding;
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

@Path("/oneToOneAFoos")
public class OneToOneAFooResourceCollectionCodegen {

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get(final @Context Repository repo) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, OneToOneAFoo.class, OneToOneAFoo.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final @Context Repository repo, final OneToOneAFooBinding oneToOneAFoo) {
    return UoW.go(repo, null, new BlockWithReturn<OneToOneAFoo>() {
      public OneToOneAFoo go() {
        OneToOneAFoo domainObject = new OneToOneAFoo();
        BindingMapper.toDomain(oneToOneAFoo, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
