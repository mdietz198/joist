package features.rs.resources;

import features.domain.ManyToManyBBar;
import features.domain.ManyToManyBBarAlias;
import features.rs.binding.ManyToManyBBarBinding;
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

@Path("/manyToManyBBars")
public class ManyToManyBBarResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public PagedCollectionBinding get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndexParam, final @QueryParam("maxResults") Integer maxResultsParam, final @QueryParam("name") String name) {
    return UoW.read(repo, new BlockWithReturn<PagedCollectionBinding>() {
      public PagedCollectionBinding go() {
        Integer startIndex = startIndexParam == null ? 0 : startIndexParam;
        Integer maxResults = maxResultsParam == null ? 20 : maxResultsParam;
        ManyToManyBBarAlias mtmbb0 = new ManyToManyBBarAlias();
        Select<ManyToManyBBar> q = Select.from(mtmbb0);
        if(name != null) {
          q.where(mtmbb0.name.eq(name));
        }
        q.orderBy(mtmbb0.id.asc());
        q.offset(startIndex);
        q.limit(maxResults );
        List<ManyToManyBBar> list = q.list();
        PagedCollectionBinding result = new PagedCollectionBinding();
        result.setLinksFromDomainObjects(list);
        if (startIndex > 0) {
          result.setPrevious(new CollectionLinkBinding(ManyToManyBBar.class, Math.max(0, startIndex - maxResults), Math.min(startIndex, maxResults)));
        }
        if (!list.isEmpty() && list.size() == maxResults) {
          result.setNext(new CollectionLinkBinding(ManyToManyBBar.class, startIndex + maxResults, maxResults));
        }
        return result;
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ManyToManyBBarBinding manyToManyBBar) {
    return UoW.go(repo, null, new BlockWithReturn<ManyToManyBBar>() {
      public ManyToManyBBar go() {
        ManyToManyBBar domainObject = new ManyToManyBBar();
        BindingMapper.toDomain(manyToManyBBar, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
