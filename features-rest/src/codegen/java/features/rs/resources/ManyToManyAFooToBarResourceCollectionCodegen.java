package features.rs.resources;

import features.domain.ManyToManyAFooToBar;
import features.rs.binding.ManyToManyAFooToBarBinding;
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

@Path("/manyToManyAFooToBars")
public class ManyToManyAFooToBarResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, ManyToManyAFooToBar.class, ManyToManyAFooToBar.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ManyToManyAFooToBarBinding manyToManyAFooToBar) {
    return UoW.go(repo, null, new BlockWithReturn<ManyToManyAFooToBar>() {
      public ManyToManyAFooToBar go() {
        ManyToManyAFooToBar domainObject = new ManyToManyAFooToBar();
        BindingMapper.toDomain(manyToManyAFooToBar, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
