package features.rs.resources;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.InheritanceASubTwo;
import features.domain.InheritanceASubTwoAlias;
import features.rs.binding.InheritanceASubTwoBinding;
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
import static features.rs.mappers.InheritanceASubTwoBindingMapper.toDomain;

@Path("/inheritanceASubTwos")
public class InheritanceASubTwoResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public PagedCollectionBinding get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndexParam, final @QueryParam("maxResults") Integer maxResultsParam, final @QueryParam("name") String name, final @QueryParam("two") String two, final @QueryParam("inheritanceAOwner") Long inheritanceAOwner, final @QueryParam("inheritanceAThing") Long inheritanceAThing) {
    return UoW.read(repo, new BlockWithReturn<PagedCollectionBinding>() {
      public PagedCollectionBinding go() {
        Integer startIndex = startIndexParam == null ? 0 : startIndexParam;
        Integer maxResults = maxResultsParam == null ? 20 : maxResultsParam;
        InheritanceASubTwoAlias iast0 = new InheritanceASubTwoAlias();
        Select<InheritanceASubTwo> q = Select.from(iast0);
        if(name != null) {
          q.where(iast0.name.eq(name));
        }
        if(two != null) {
          q.where(iast0.two.eq(two));
        }
        if(inheritanceAOwner != null) {
          q.where(iast0.inheritanceAOwner.eq(inheritanceAOwner));
        }
        if(inheritanceAThing != null) {
          q.where(iast0.inheritanceAThing.eq(inheritanceAThing));
        }
        q.orderBy(iast0.id.asc());
        q.offset(startIndex);
        q.limit(maxResults );
        List<InheritanceASubTwo> list = q.list();
        PagedCollectionBinding result = new PagedCollectionBinding();
        result.setLinksFromDomainObjects(list);
        if (startIndex > 0) {
          result.setPrevious(new CollectionLinkBinding("previous", new UriBuilderImpl().path(InheritanceASubTwoResourceCollectionCodegen.class) .queryParam("startIndex", Math.max(0, startIndex - maxResults)).queryParam("maxResults", Math.min(startIndex, maxResults)).build().toString()));
        }
        if (!list.isEmpty() && list.size() == maxResults) {
          result.setNext(new CollectionLinkBinding("next", new UriBuilderImpl().path(InheritanceASubTwoResourceCollectionCodegen.class).queryParam("startIndex", startIndex + maxResults).queryParam("maxResults", maxResults).build().toString()));
        }
        return result;
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final InheritanceASubTwoBinding inheritanceASubTwo) {
    return UoW.go(repo, null, new BlockWithReturn<InheritanceASubTwo>() {
      public InheritanceASubTwo go() {
        InheritanceASubTwo domainObject = new InheritanceASubTwo();
        toDomain(inheritanceASubTwo, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
