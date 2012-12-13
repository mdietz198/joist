package features.rs.resources;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.InheritanceAThing;
import features.domain.InheritanceAThingAlias;
import features.rs.binding.InheritanceAThingBinding;
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
import static features.rs.mappers.InheritanceAThingBindingMapper.toDomain;

@Path("/inheritanceAThings")
public class InheritanceAThingResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public PagedCollectionBinding get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndexParam, final @QueryParam("maxResults") Integer maxResultsParam, final @QueryParam("name") String name) {
    return UoW.read(repo, new BlockWithReturn<PagedCollectionBinding>() {
      public PagedCollectionBinding go() {
        Integer startIndex = startIndexParam == null ? 0 : startIndexParam;
        Integer maxResults = maxResultsParam == null ? 20 : maxResultsParam;
        InheritanceAThingAlias iat0 = new InheritanceAThingAlias();
        Select<InheritanceAThing> q = Select.from(iat0);
        if(name != null) {
          q.where(iat0.name.eq(name));
        }
        q.orderBy(iat0.id.asc());
        q.offset(startIndex);
        q.limit(maxResults );
        List<InheritanceAThing> list = q.list();
        PagedCollectionBinding result = new PagedCollectionBinding();
        result.setLinksFromDomainObjects(list);
        if (startIndex > 0) {
          result.setPrevious(new CollectionLinkBinding("previous", new UriBuilderImpl().path(InheritanceAThingResourceCollectionCodegen.class) .queryParam("startIndex", Math.max(0, startIndex - maxResults)).queryParam("maxResults", Math.min(startIndex, maxResults)).build().toString()));
        }
        if (!list.isEmpty() && list.size() == maxResults) {
          result.setNext(new CollectionLinkBinding("next", new UriBuilderImpl().path(InheritanceAThingResourceCollectionCodegen.class).queryParam("startIndex", startIndex + maxResults).queryParam("maxResults", maxResults).build().toString()));
        }
        return result;
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final InheritanceAThingBinding inheritanceAThing) {
    return UoW.go(repo, null, new BlockWithReturn<InheritanceAThing>() {
      public InheritanceAThing go() {
        InheritanceAThing domainObject = new InheritanceAThing();
        toDomain(inheritanceAThing, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
