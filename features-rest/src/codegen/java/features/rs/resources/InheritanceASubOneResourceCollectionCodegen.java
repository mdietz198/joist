package features.rs.resources;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.InheritanceASubOne;
import features.domain.InheritanceASubOneAlias;
import features.rs.binding.InheritanceASubOneBinding;
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
import static features.rs.mappers.InheritanceASubOneBindingMapper.toDomain;

@Path("/inheritanceASubOnes")
public class InheritanceASubOneResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public PagedCollectionBinding get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndexParam, final @QueryParam("maxResults") Integer maxResultsParam, final @QueryParam("name") String name, final @QueryParam("one") String one, final @QueryParam("inheritanceAOwner") Long inheritanceAOwner, final @QueryParam("inheritanceAThing") Long inheritanceAThing) {
    return UoW.read(repo, new BlockWithReturn<PagedCollectionBinding>() {
      public PagedCollectionBinding go() {
        Integer startIndex = startIndexParam == null ? 0 : startIndexParam;
        Integer maxResults = maxResultsParam == null ? 20 : maxResultsParam;
        InheritanceASubOneAlias iaso0 = new InheritanceASubOneAlias();
        Select<InheritanceASubOne> q = Select.from(iaso0);
        if(name != null) {
          q.where(iaso0.name.eq(name));
        }
        if(one != null) {
          q.where(iaso0.one.eq(one));
        }
        if(inheritanceAOwner != null) {
          q.where(iaso0.inheritanceAOwner.eq(inheritanceAOwner));
        }
        if(inheritanceAThing != null) {
          q.where(iaso0.inheritanceAThing.eq(inheritanceAThing));
        }
        q.orderBy(iaso0.id.asc());
        q.offset(startIndex);
        q.limit(maxResults );
        List<InheritanceASubOne> list = q.list();
        PagedCollectionBinding result = new PagedCollectionBinding();
        result.setLinksFromDomainObjects(list);
        if (startIndex > 0) {
          result.setPrevious(new CollectionLinkBinding("previous", new UriBuilderImpl().path(InheritanceASubOneResourceCollectionCodegen.class) .queryParam("startIndex", Math.max(0, startIndex - maxResults)).queryParam("maxResults", Math.min(startIndex, maxResults)).build().toString()));
        }
        if (!list.isEmpty() && list.size() == maxResults) {
          result.setNext(new CollectionLinkBinding("next", new UriBuilderImpl().path(InheritanceASubOneResourceCollectionCodegen.class).queryParam("startIndex", startIndex + maxResults).queryParam("maxResults", maxResults).build().toString()));
        }
        return result;
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final InheritanceASubOneBinding inheritanceASubOne) {
    return UoW.go(repo, null, new BlockWithReturn<InheritanceASubOne>() {
      public InheritanceASubOne go() {
        InheritanceASubOne domainObject = new InheritanceASubOne();
        toDomain(inheritanceASubOne, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
