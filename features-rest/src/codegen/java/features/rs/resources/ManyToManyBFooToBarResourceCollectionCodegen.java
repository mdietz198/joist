package features.rs.resources;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.ManyToManyBFooToBar;
import features.domain.ManyToManyBFooToBarAlias;
import features.rs.binding.ManyToManyBFooToBarBinding;
import features.rs.helpers.BindingMapper;
import java.util.List;
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
import joist.rs.CollectionLinkBinding;
import joist.rs.PagedCollectionBinding;

@Path("/manyToManyBFooToBars")
public class ManyToManyBFooToBarResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public PagedCollectionBinding get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndexParam, final @QueryParam("maxResults") Integer maxResultsParam, final @QueryParam("owned") Long owned, final @QueryParam("ownerManyToManyBFoo") Long ownerManyToManyBFoo) {
    return UoW.read(repo, new BlockWithReturn<PagedCollectionBinding>() {
      public PagedCollectionBinding go() {
        Integer startIndex = startIndexParam == null ? 0 : startIndexParam;
        Integer maxResults = maxResultsParam == null ? 20 : maxResultsParam;
        ManyToManyBFooToBarAlias mtmbftb0 = new ManyToManyBFooToBarAlias();
        Select<ManyToManyBFooToBar> q = Select.from(mtmbftb0);
        if(owned != null) {
          q.where(mtmbftb0.owned.eq(owned));
        }
        if(ownerManyToManyBFoo != null) {
          q.where(mtmbftb0.ownerManyToManyBFoo.eq(ownerManyToManyBFoo));
        }
        q.orderBy(mtmbftb0.id.asc());
        q.offset(startIndex);
        q.limit(maxResults );
        List<ManyToManyBFooToBar> list = q.list();
        PagedCollectionBinding result = new PagedCollectionBinding();
        result.setLinksFromDomainObjects(list);
        if (startIndex > 0) {
          result.setPrevious(new CollectionLinkBinding("previous", new UriBuilderImpl().path(ManyToManyBFooToBarResourceCollectionCodegen.class) .queryParam("startIndex", Math.max(0, startIndex - maxResults)).queryParam("maxResults", Math.min(startIndex, maxResults)).build().toString()));
        }
        if (!list.isEmpty() && list.size() == maxResults) {
          result.setNext(new CollectionLinkBinding("next", new UriBuilderImpl().path(ManyToManyBFooToBarResourceCollectionCodegen.class).queryParam("startIndex", startIndex + maxResults).queryParam("maxResults", maxResults).build().toString()));
        }
        return result;
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
