package features.rs.resources;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.ParentBChildZaz;
import features.domain.ParentBChildZazAlias;
import features.rs.binding.ParentBChildZazBinding;
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
import static features.rs.mappers.ParentBChildZazBindingMapper.toDomain;

@Path("/parentBChildZazs")
public class ParentBChildZazResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public PagedCollectionBinding get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndexParam, final @QueryParam("maxResults") Integer maxResultsParam, final @QueryParam("name") String name, final @QueryParam("parentBChildBar") Long parentBChildBar, final @QueryParam("parentBParent") Long parentBParent) {
    return UoW.read(repo, new BlockWithReturn<PagedCollectionBinding>() {
      public PagedCollectionBinding go() {
        Integer startIndex = startIndexParam == null ? 0 : startIndexParam;
        Integer maxResults = maxResultsParam == null ? 20 : maxResultsParam;
        ParentBChildZazAlias pbcz0 = new ParentBChildZazAlias();
        Select<ParentBChildZaz> q = Select.from(pbcz0);
        if(name != null) {
          q.where(pbcz0.name.eq(name));
        }
        if(parentBChildBar != null) {
          q.where(pbcz0.parentBChildBar.eq(parentBChildBar));
        }
        if(parentBParent != null) {
          q.where(pbcz0.parentBParent.eq(parentBParent));
        }
        q.orderBy(pbcz0.id.asc());
        q.offset(startIndex);
        q.limit(maxResults );
        List<ParentBChildZaz> list = q.list();
        PagedCollectionBinding result = new PagedCollectionBinding();
        result.setLinksFromDomainObjects(list);
        if (startIndex > 0) {
          result.setPrevious(new CollectionLinkBinding("previous", new UriBuilderImpl().path(ParentBChildZazResourceCollectionCodegen.class) .queryParam("startIndex", Math.max(0, startIndex - maxResults)).queryParam("maxResults", Math.min(startIndex, maxResults)).build().toString()));
        }
        if (!list.isEmpty() && list.size() == maxResults) {
          result.setNext(new CollectionLinkBinding("next", new UriBuilderImpl().path(ParentBChildZazResourceCollectionCodegen.class).queryParam("startIndex", startIndex + maxResults).queryParam("maxResults", maxResults).build().toString()));
        }
        return result;
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ParentBChildZazBinding parentBChildZaz) {
    return UoW.go(repo, null, new BlockWithReturn<ParentBChildZaz>() {
      public ParentBChildZaz go() {
        ParentBChildZaz domainObject = new ParentBChildZaz();
        toDomain(parentBChildZaz, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
