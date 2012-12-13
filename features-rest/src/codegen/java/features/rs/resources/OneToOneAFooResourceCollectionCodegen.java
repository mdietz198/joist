package features.rs.resources;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.OneToOneAFoo;
import features.domain.OneToOneAFooAlias;
import features.rs.binding.OneToOneAFooBinding;
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

@Path("/oneToOneAFoos")
public class OneToOneAFooResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public PagedCollectionBinding get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndexParam, final @QueryParam("maxResults") Integer maxResultsParam, final @QueryParam("name") String name) {
    return UoW.read(repo, new BlockWithReturn<PagedCollectionBinding>() {
      public PagedCollectionBinding go() {
        Integer startIndex = startIndexParam == null ? 0 : startIndexParam;
        Integer maxResults = maxResultsParam == null ? 20 : maxResultsParam;
        OneToOneAFooAlias otoaf0 = new OneToOneAFooAlias();
        Select<OneToOneAFoo> q = Select.from(otoaf0);
        if(name != null) {
          q.where(otoaf0.name.eq(name));
        }
        q.orderBy(otoaf0.id.asc());
        q.offset(startIndex);
        q.limit(maxResults );
        List<OneToOneAFoo> list = q.list();
        PagedCollectionBinding result = new PagedCollectionBinding();
        result.setLinksFromDomainObjects(list);
        if (startIndex > 0) {
          result.setPrevious(new CollectionLinkBinding("previous", new UriBuilderImpl().path(OneToOneAFooResourceCollectionCodegen.class) .queryParam("startIndex", Math.max(0, startIndex - maxResults)).queryParam("maxResults", Math.min(startIndex, maxResults)).build().toString()));
        }
        if (!list.isEmpty() && list.size() == maxResults) {
          result.setNext(new CollectionLinkBinding("next", new UriBuilderImpl().path(OneToOneAFooResourceCollectionCodegen.class).queryParam("startIndex", startIndex + maxResults).queryParam("maxResults", maxResults).build().toString()));
        }
        return result;
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final OneToOneAFooBinding oneToOneAFoo) {
    return UoW.go(repo, null, new BlockWithReturn<OneToOneAFoo>() {
      public OneToOneAFoo go() {
        OneToOneAFoo domainObject = new OneToOneAFoo();
        BindingMapper.toDomain(oneToOneAFoo, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
