package features.rs.resources;

import features.domain.ManyToManyBFooToBar;
import features.domain.ManyToManyBFooToBarAlias;
import features.rs.binding.ManyToManyBFooToBarBinding;
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

@Path("/manyToManyBFooToBars")
public class ManyToManyBFooToBarResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndex, final @QueryParam("maxResults") Integer maxResults, final @QueryParam("owned") Long owned, final @QueryParam("ownerManyToManyBFoo") Long ownerManyToManyBFoo) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        ManyToManyBFooToBarAlias mtmbftb0 = new ManyToManyBFooToBarAlias();
        Select<ManyToManyBFooToBar> q = Select.from(mtmbftb0);
        if(owned != null) {
          q.where(mtmbftb0.owned.eq(owned));
        }
        if(ownerManyToManyBFoo != null) {
          q.where(mtmbftb0.ownerManyToManyBFoo.eq(ownerManyToManyBFoo));
        }
        q.orderBy(mtmbftb0.id.asc());
        q.offset(startIndex == null ? 0 : startIndex);
        q.limit(maxResults == null ? 20: maxResults);
        return new LinkCollection(0, q.list());
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
