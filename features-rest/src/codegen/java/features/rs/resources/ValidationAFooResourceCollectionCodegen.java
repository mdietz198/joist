package features.rs.resources;

import features.domain.ValidationAFoo;
import features.domain.ValidationAFooAlias;
import features.rs.binding.ValidationAFooBinding;
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

@Path("/validationAFoos")
public class ValidationAFooResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public PagedCollectionBinding get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndexParam, final @QueryParam("maxResults") Integer maxResultsParam, final @QueryParam("name") String name) {
    return UoW.read(repo, new BlockWithReturn<PagedCollectionBinding>() {
      public PagedCollectionBinding go() {
        Integer startIndex = startIndexParam == null ? 0 : startIndexParam;
        Integer maxResults = maxResultsParam == null ? 20 : maxResultsParam;
        ValidationAFooAlias vaf0 = new ValidationAFooAlias();
        Select<ValidationAFoo> q = Select.from(vaf0);
        if(name != null) {
          q.where(vaf0.name.eq(name));
        }
        q.orderBy(vaf0.id.asc());
        q.offset(startIndex);
        q.limit(maxResults );
        List<ValidationAFoo> list = q.list();
        PagedCollectionBinding result = new PagedCollectionBinding();
        result.setLinksFromDomainObjects(list);
        if (startIndex > 0) {
          result.setPrevious(new CollectionLinkBinding(ValidationAFoo.class, Math.max(0, startIndex - maxResults), Math.min(startIndex, maxResults)));
        }
        if (!list.isEmpty() && list.size() == maxResults) {
          result.setNext(new CollectionLinkBinding(ValidationAFoo.class, startIndex + maxResults, maxResults));
        }
        return result;
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ValidationAFooBinding validationAFoo) {
    return UoW.go(repo, null, new BlockWithReturn<ValidationAFoo>() {
      public ValidationAFoo go() {
        ValidationAFoo domainObject = new ValidationAFoo();
        BindingMapper.toDomain(validationAFoo, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
