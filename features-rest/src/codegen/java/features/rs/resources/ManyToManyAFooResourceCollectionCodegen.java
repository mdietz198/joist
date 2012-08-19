package features.rs.resources;

import features.domain.ManyToManyAFoo;
import features.rs.binding.ManyToManyAFooBinding;
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

@Path("/manyToManyAFoos")
public class ManyToManyAFooResourceCollectionCodegen {

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get(final @Context Repository repo) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, ManyToManyAFoo.class, ManyToManyAFoo.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final @Context Repository repo, final ManyToManyAFooBinding manyToManyAFoo) {
    return UoW.go(repo, null, new BlockWithReturn<ManyToManyAFoo>() {
      public ManyToManyAFoo go() {
        ManyToManyAFoo domainObject = new ManyToManyAFoo();
        BindingMapper.toDomain(manyToManyAFoo, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
