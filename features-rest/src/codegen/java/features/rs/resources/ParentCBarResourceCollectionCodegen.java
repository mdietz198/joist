package features.rs.resources;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.ParentCBar;
import features.domain.ParentCBarAlias;
import features.rs.binding.ParentCBarBinding;
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
import static features.rs.mappers.ParentCBarBindingMapper.toDomain;

@Path("/parentCBars")
public class ParentCBarResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public PagedCollectionBinding get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndexParam, final @QueryParam("maxResults") Integer maxResultsParam, final @QueryParam("name") String name, final @QueryParam("firstParent") Long firstParent, final @QueryParam("secondParent") Long secondParent) {
    return UoW.read(repo, new BlockWithReturn<PagedCollectionBinding>() {
      public PagedCollectionBinding go() {
        Integer startIndex = startIndexParam == null ? 0 : startIndexParam;
        Integer maxResults = maxResultsParam == null ? 20 : maxResultsParam;
        ParentCBarAlias pcb0 = new ParentCBarAlias();
        Select<ParentCBar> q = Select.from(pcb0);
        if(name != null) {
          q.where(pcb0.name.eq(name));
        }
        if(firstParent != null) {
          q.where(pcb0.firstParent.eq(firstParent));
        }
        if(secondParent != null) {
          q.where(pcb0.secondParent.eq(secondParent));
        }
        q.orderBy(pcb0.id.asc());
        q.offset(startIndex);
        q.limit(maxResults );
        List<ParentCBar> list = q.list();
        PagedCollectionBinding result = new PagedCollectionBinding();
        result.setLinksFromDomainObjects(list);
        if (startIndex > 0) {
          result.setPrevious(new CollectionLinkBinding("previous", new UriBuilderImpl().path(ParentCBarResourceCollectionCodegen.class) .queryParam("startIndex", Math.max(0, startIndex - maxResults)).queryParam("maxResults", Math.min(startIndex, maxResults)).build().toString()));
        }
        if (!list.isEmpty() && list.size() == maxResults) {
          result.setNext(new CollectionLinkBinding("next", new UriBuilderImpl().path(ParentCBarResourceCollectionCodegen.class).queryParam("startIndex", startIndex + maxResults).queryParam("maxResults", maxResults).build().toString()));
        }
        return result;
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ParentCBarBinding parentCBar) {
    return UoW.go(repo, null, new BlockWithReturn<ParentCBar>() {
      public ParentCBar go() {
        ParentCBar domainObject = new ParentCBar();
        toDomain(parentCBar, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
