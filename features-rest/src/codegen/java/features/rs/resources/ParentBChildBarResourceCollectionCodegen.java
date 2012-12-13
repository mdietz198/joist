package features.rs.resources;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.ParentBChildBar;
import features.domain.ParentBChildBarAlias;
import features.rs.binding.ParentBChildBarBinding;
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

@Path("/parentBChildBars")
public class ParentBChildBarResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public PagedCollectionBinding get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndexParam, final @QueryParam("maxResults") Integer maxResultsParam, final @QueryParam("name") String name, final @QueryParam("parentBParent") Long parentBParent) {
    return UoW.read(repo, new BlockWithReturn<PagedCollectionBinding>() {
      public PagedCollectionBinding go() {
        Integer startIndex = startIndexParam == null ? 0 : startIndexParam;
        Integer maxResults = maxResultsParam == null ? 20 : maxResultsParam;
        ParentBChildBarAlias pbcb0 = new ParentBChildBarAlias();
        Select<ParentBChildBar> q = Select.from(pbcb0);
        if(name != null) {
          q.where(pbcb0.name.eq(name));
        }
        if(parentBParent != null) {
          q.where(pbcb0.parentBParent.eq(parentBParent));
        }
        q.orderBy(pbcb0.id.asc());
        q.offset(startIndex);
        q.limit(maxResults );
        List<ParentBChildBar> list = q.list();
        PagedCollectionBinding result = new PagedCollectionBinding();
        result.setLinksFromDomainObjects(list);
        if (startIndex > 0) {
          result.setPrevious(new CollectionLinkBinding("previous", new UriBuilderImpl().path(ParentBChildBarResourceCollectionCodegen.class) .queryParam("startIndex", Math.max(0, startIndex - maxResults)).queryParam("maxResults", Math.min(startIndex, maxResults)).build().toString()));
        }
        if (!list.isEmpty() && list.size() == maxResults) {
          result.setNext(new CollectionLinkBinding("next", new UriBuilderImpl().path(ParentBChildBarResourceCollectionCodegen.class).queryParam("startIndex", startIndex + maxResults).queryParam("maxResults", maxResults).build().toString()));
        }
        return result;
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ParentBChildBarBinding parentBChildBar) {
    return UoW.go(repo, null, new BlockWithReturn<ParentBChildBar>() {
      public ParentBChildBar go() {
        ParentBChildBar domainObject = new ParentBChildBar();
        BindingMapper.toDomain(parentBChildBar, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
