package features.rs.resources;

import features.domain.ManyToManyBFoo;
import features.domain.ManyToManyBFooAlias;
import features.rs.binding.ManyToManyBFooBinding;
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

@Path("/manyToManyBFoos")
public class ManyToManyBFooResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public PagedCollectionBinding get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndexParam, final @QueryParam("maxResults") Integer maxResultsParam, final @QueryParam("name") String name) {
    return UoW.read(repo, new BlockWithReturn<PagedCollectionBinding>() {
      public PagedCollectionBinding go() {
        Integer startIndex = startIndexParam == null ? 0 : startIndexParam;
        Integer maxResults = maxResultsParam == null ? 20 : maxResultsParam;
        ManyToManyBFooAlias mtmbf0 = new ManyToManyBFooAlias();
        Select<ManyToManyBFoo> q = Select.from(mtmbf0);
        if(name != null) {
          q.where(mtmbf0.name.eq(name));
        }
        q.orderBy(mtmbf0.id.asc());
        q.offset(startIndex);
        q.limit(maxResults );
        List<ManyToManyBFoo> list = q.list();
        PagedCollectionBinding result = new PagedCollectionBinding();
        result.setLinksFromDomainObjects(list);
        if (startIndex > 0) {
          result.setPrevious(new CollectionLinkBinding(ManyToManyBFoo.class, Math.max(0, startIndex - maxResults), Math.min(startIndex, maxResults)));
        }
        if (!list.isEmpty() && list.size() == maxResults) {
          result.setNext(new CollectionLinkBinding(ManyToManyBFoo.class, startIndex + maxResults, maxResults));
        }
        return result;
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ManyToManyBFooBinding manyToManyBFoo) {
    return UoW.go(repo, null, new BlockWithReturn<ManyToManyBFoo>() {
      public ManyToManyBFoo go() {
        ManyToManyBFoo domainObject = new ManyToManyBFoo();
        BindingMapper.toDomain(manyToManyBFoo, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
