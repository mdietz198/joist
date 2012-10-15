package features.rs.resources;

import features.domain.ManyToManyBFoo;
import features.rs.binding.ManyToManyBFooBinding;
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

@Path("/manyToManyBFoos")
public class ManyToManyBFooResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, ManyToManyBFoo.class, ManyToManyBFoo.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ManyToManyBFooBinding manyToManyBFoo) {
    return UoW.go(repo, null, new BlockWithReturn<ManyToManyBFoo>() {
      public ManyToManyBFoo go() {
        ManyToManyBFoo domainObject = new ManyToManyBFoo();
        BindingMapper.toDomain(manyToManyBFoo, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
