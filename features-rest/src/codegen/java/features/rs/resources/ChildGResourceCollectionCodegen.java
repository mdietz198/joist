package features.rs.resources;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.ChildG;
import features.domain.ChildGAlias;
import features.rs.binding.ChildGBinding;
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

@Path("/childGs")
public class ChildGResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public PagedCollectionBinding get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndexParam, final @QueryParam("maxResults") Integer maxResultsParam, final @QueryParam("name") String name, final @QueryParam("parentOne") Long parentOne, final @QueryParam("parentTwo") Long parentTwo) {
    return UoW.read(repo, new BlockWithReturn<PagedCollectionBinding>() {
      public PagedCollectionBinding go() {
        Integer startIndex = startIndexParam == null ? 0 : startIndexParam;
        Integer maxResults = maxResultsParam == null ? 20 : maxResultsParam;
        ChildGAlias cg0 = new ChildGAlias();
        Select<ChildG> q = Select.from(cg0);
        if(name != null) {
          q.where(cg0.name.eq(name));
        }
        if(parentOne != null) {
          q.where(cg0.parentOne.eq(parentOne));
        }
        if(parentTwo != null) {
          q.where(cg0.parentTwo.eq(parentTwo));
        }
        q.orderBy(cg0.id.asc());
        q.offset(startIndex);
        q.limit(maxResults );
        List<ChildG> list = q.list();
        PagedCollectionBinding result = new PagedCollectionBinding();
        result.setLinksFromDomainObjects(list);
        if (startIndex > 0) {
          result.setPrevious(new CollectionLinkBinding("previous", new UriBuilderImpl().path(ChildGResourceCollectionCodegen.class) .queryParam("startIndex", Math.max(0, startIndex - maxResults)).queryParam("maxResults", Math.min(startIndex, maxResults)).build().toString()));
        }
        if (!list.isEmpty() && list.size() == maxResults) {
          result.setNext(new CollectionLinkBinding("next", new UriBuilderImpl().path(ChildGResourceCollectionCodegen.class).queryParam("startIndex", startIndex + maxResults).queryParam("maxResults", maxResults).build().toString()));
        }
        return result;
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ChildGBinding childG) {
    return UoW.go(repo, null, new BlockWithReturn<ChildG>() {
      public ChildG go() {
        ChildG domainObject = new ChildG();
        BindingMapper.toDomain(childG, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
