package features.rs.resources;

import features.domain.ValuesA;
import features.domain.ValuesAAlias;
import features.rs.binding.ValuesABinding;
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

@Path("/valuesAs")
public class ValuesAResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public PagedCollectionBinding get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndexParam, final @QueryParam("maxResults") Integer maxResultsParam, final @QueryParam("a") String a, final @QueryParam("b") String b, final @QueryParam("i") Integer i, final @QueryParam("j") Integer j, final @QueryParam("name") String name) {
    return UoW.read(repo, new BlockWithReturn<PagedCollectionBinding>() {
      public PagedCollectionBinding go() {
        Integer startIndex = startIndexParam == null ? 0 : startIndexParam;
        Integer maxResults = maxResultsParam == null ? 20 : maxResultsParam;
        ValuesAAlias va0 = new ValuesAAlias();
        Select<ValuesA> q = Select.from(va0);
        if(a != null) {
          q.where(va0.a.eq(a));
        }
        if(b != null) {
          q.where(va0.b.eq(b));
        }
        if(i != null) {
          q.where(va0.i.eq(i));
        }
        if(j != null) {
          q.where(va0.j.eq(j));
        }
        if(name != null) {
          q.where(va0.name.eq(name));
        }
        q.orderBy(va0.id.asc());
        q.offset(startIndex);
        q.limit(maxResults );
        List<ValuesA> list = q.list();
        PagedCollectionBinding result = new PagedCollectionBinding();
        result.setLinksFromDomainObjects(list);
        if (startIndex > 0) {
          result.setPrevious(new CollectionLinkBinding(ValuesA.class, Math.max(0, startIndex - maxResults), Math.min(startIndex, maxResults)));
        }
        if (!list.isEmpty() && list.size() == maxResults) {
          result.setNext(new CollectionLinkBinding(ValuesA.class, startIndex + maxResults, maxResults));
        }
        return result;
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ValuesABinding valuesA) {
    return UoW.go(repo, null, new BlockWithReturn<ValuesA>() {
      public ValuesA go() {
        ValuesA domainObject = new ValuesA();
        BindingMapper.toDomain(valuesA, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
