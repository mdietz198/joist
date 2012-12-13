package features.rs.resources;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.GrandChild;
import features.domain.GrandChildAlias;
import features.rs.binding.GrandChildBinding;
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

@Path("/grandChilds")
public class GrandChildResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public PagedCollectionBinding get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndexParam, final @QueryParam("maxResults") Integer maxResultsParam, final @QueryParam("name") String name, final @QueryParam("child") Long child) {
    return UoW.read(repo, new BlockWithReturn<PagedCollectionBinding>() {
      public PagedCollectionBinding go() {
        Integer startIndex = startIndexParam == null ? 0 : startIndexParam;
        Integer maxResults = maxResultsParam == null ? 20 : maxResultsParam;
        GrandChildAlias gc0 = new GrandChildAlias();
        Select<GrandChild> q = Select.from(gc0);
        if(name != null) {
          q.where(gc0.name.eq(name));
        }
        if(child != null) {
          q.where(gc0.child.eq(child));
        }
        q.orderBy(gc0.id.asc());
        q.offset(startIndex);
        q.limit(maxResults );
        List<GrandChild> list = q.list();
        PagedCollectionBinding result = new PagedCollectionBinding();
        result.setLinksFromDomainObjects(list);
        if (startIndex > 0) {
          result.setPrevious(new CollectionLinkBinding("previous", new UriBuilderImpl().path(GrandChildResourceCollectionCodegen.class) .queryParam("startIndex", Math.max(0, startIndex - maxResults)).queryParam("maxResults", Math.min(startIndex, maxResults)).build().toString()));
        }
        if (!list.isEmpty() && list.size() == maxResults) {
          result.setNext(new CollectionLinkBinding("next", new UriBuilderImpl().path(GrandChildResourceCollectionCodegen.class).queryParam("startIndex", startIndex + maxResults).queryParam("maxResults", maxResults).build().toString()));
        }
        return result;
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final GrandChildBinding grandChild) {
    return UoW.go(repo, null, new BlockWithReturn<GrandChild>() {
      public GrandChild go() {
        GrandChild domainObject = new GrandChild();
        BindingMapper.toDomain(grandChild, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
