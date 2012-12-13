package features.rs.resources;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.ParentDChildB;
import features.domain.ParentDChildBAlias;
import features.rs.binding.ParentDChildBBinding;
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

@Path("/parentDChildBs")
public class ParentDChildBResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public PagedCollectionBinding get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndexParam, final @QueryParam("maxResults") Integer maxResultsParam, final @QueryParam("name") String name, final @QueryParam("parentD") Long parentD) {
    return UoW.read(repo, new BlockWithReturn<PagedCollectionBinding>() {
      public PagedCollectionBinding go() {
        Integer startIndex = startIndexParam == null ? 0 : startIndexParam;
        Integer maxResults = maxResultsParam == null ? 20 : maxResultsParam;
        ParentDChildBAlias pdcb0 = new ParentDChildBAlias();
        Select<ParentDChildB> q = Select.from(pdcb0);
        if(name != null) {
          q.where(pdcb0.name.eq(name));
        }
        if(parentD != null) {
          q.where(pdcb0.parentD.eq(parentD));
        }
        q.orderBy(pdcb0.id.asc());
        q.offset(startIndex);
        q.limit(maxResults );
        List<ParentDChildB> list = q.list();
        PagedCollectionBinding result = new PagedCollectionBinding();
        result.setLinksFromDomainObjects(list);
        if (startIndex > 0) {
          result.setPrevious(new CollectionLinkBinding("previous", new UriBuilderImpl().path(ParentDChildBResourceCollectionCodegen.class) .queryParam("startIndex", Math.max(0, startIndex - maxResults)).queryParam("maxResults", Math.min(startIndex, maxResults)).build().toString()));
        }
        if (!list.isEmpty() && list.size() == maxResults) {
          result.setNext(new CollectionLinkBinding("next", new UriBuilderImpl().path(ParentDChildBResourceCollectionCodegen.class).queryParam("startIndex", startIndex + maxResults).queryParam("maxResults", maxResults).build().toString()));
        }
        return result;
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ParentDChildBBinding parentDChildB) {
    return UoW.go(repo, null, new BlockWithReturn<ParentDChildB>() {
      public ParentDChildB go() {
        ParentDChildB domainObject = new ParentDChildB();
        BindingMapper.toDomain(parentDChildB, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
