package features.rs.resources;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.ChildIA;
import features.domain.ChildIAAlias;
import features.rs.binding.ChildIABinding;
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
import static features.rs.mappers.ChildIABindingMapper.toDomain;

@Path("/childIAs")
public class ChildIAResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public PagedCollectionBinding get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndexParam, final @QueryParam("maxResults") Integer maxResultsParam, final @QueryParam("parent") Long parent) {
    return UoW.read(repo, new BlockWithReturn<PagedCollectionBinding>() {
      public PagedCollectionBinding go() {
        Integer startIndex = startIndexParam == null ? 0 : startIndexParam;
        Integer maxResults = maxResultsParam == null ? 20 : maxResultsParam;
        ChildIAAlias cia0 = new ChildIAAlias();
        Select<ChildIA> q = Select.from(cia0);
        if(parent != null) {
          q.where(cia0.parent.eq(parent));
        }
        q.orderBy(cia0.id.asc());
        q.offset(startIndex);
        q.limit(maxResults );
        List<ChildIA> list = q.list();
        PagedCollectionBinding result = new PagedCollectionBinding();
        result.setLinksFromDomainObjects(list);
        if (startIndex > 0) {
          result.setPrevious(new CollectionLinkBinding("previous", new UriBuilderImpl().path(ChildIAResourceCollectionCodegen.class) .queryParam("startIndex", Math.max(0, startIndex - maxResults)).queryParam("maxResults", Math.min(startIndex, maxResults)).build().toString()));
        }
        if (!list.isEmpty() && list.size() == maxResults) {
          result.setNext(new CollectionLinkBinding("next", new UriBuilderImpl().path(ChildIAResourceCollectionCodegen.class).queryParam("startIndex", startIndex + maxResults).queryParam("maxResults", maxResults).build().toString()));
        }
        return result;
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ChildIABinding childIA) {
    return UoW.go(repo, null, new BlockWithReturn<ChildIA>() {
      public ChildIA go() {
        ChildIA domainObject = new ChildIA();
        toDomain(childIA, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
