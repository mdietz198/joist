package features.rs.resources;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.ChildIB;
import features.domain.ChildIBAlias;
import features.rs.binding.ChildIBBinding;
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
import static features.rs.mappers.ChildIBBindingMapper.toDomain;

@Path("/childIBs")
public class ChildIBResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public PagedCollectionBinding get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndexParam, final @QueryParam("maxResults") Integer maxResultsParam, final @QueryParam("parent") Long parent) {
    return UoW.read(repo, new BlockWithReturn<PagedCollectionBinding>() {
      public PagedCollectionBinding go() {
        Integer startIndex = startIndexParam == null ? 0 : startIndexParam;
        Integer maxResults = maxResultsParam == null ? 20 : maxResultsParam;
        ChildIBAlias cib0 = new ChildIBAlias();
        Select<ChildIB> q = Select.from(cib0);
        if(parent != null) {
          q.where(cib0.parent.eq(parent));
        }
        q.orderBy(cib0.id.asc());
        q.offset(startIndex);
        q.limit(maxResults );
        List<ChildIB> list = q.list();
        PagedCollectionBinding result = new PagedCollectionBinding();
        result.setLinksFromDomainObjects(list);
        if (startIndex > 0) {
          result.setPrevious(new CollectionLinkBinding("previous", new UriBuilderImpl().path(ChildIBResourceCollectionCodegen.class) .queryParam("startIndex", Math.max(0, startIndex - maxResults)).queryParam("maxResults", Math.min(startIndex, maxResults)).build().toString()));
        }
        if (!list.isEmpty() && list.size() == maxResults) {
          result.setNext(new CollectionLinkBinding("next", new UriBuilderImpl().path(ChildIBResourceCollectionCodegen.class).queryParam("startIndex", startIndex + maxResults).queryParam("maxResults", maxResults).build().toString()));
        }
        return result;
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ChildIBBinding childIB) {
    return UoW.go(repo, null, new BlockWithReturn<ChildIB>() {
      public ChildIB go() {
        ChildIB domainObject = new ChildIB();
        toDomain(childIB, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
