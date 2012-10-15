package features.rs.resources;

import features.domain.ManyToManyBFooToBar;
import features.rs.binding.ManyToManyBFooToBarBinding;
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

@Path("/manyToManyBFooToBars")
public class ManyToManyBFooToBarResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, ManyToManyBFooToBar.class, ManyToManyBFooToBar.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ManyToManyBFooToBarBinding manyToManyBFooToBar) {
    return UoW.go(repo, null, new BlockWithReturn<ManyToManyBFooToBar>() {
      public ManyToManyBFooToBar go() {
        ManyToManyBFooToBar domainObject = new ManyToManyBFooToBar();
        BindingMapper.toDomain(manyToManyBFooToBar, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
