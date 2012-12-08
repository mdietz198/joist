package features.rs.resources;

import features.domain.InheritanceCFoo2;
import features.domain.InheritanceCFoo2Alias;
import features.rs.binding.InheritanceCFoo2Binding;
import features.rs.helpers.BindingMapper;
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
import joist.rs.LinkCollection;

@Path("/inheritanceCFoo2s")
public class InheritanceCFoo2ResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndex, final @QueryParam("maxResults") Integer maxResults, final @QueryParam("name") String name, final @QueryParam("foo") String foo) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        InheritanceCFoo2Alias icf0 = new InheritanceCFoo2Alias();
        Select<InheritanceCFoo2> q = Select.from(icf0);
        if(name != null) {
          q.where(icf0.name.eq(name));
        }
        if(foo != null) {
          q.where(icf0.foo.eq(foo));
        }
        q.orderBy(icf0.id.asc());
        q.offset(startIndex == null ? 0 : startIndex);
        q.limit(maxResults == null ? 20: maxResults);
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final InheritanceCFoo2Binding inheritanceCFoo2) {
    return UoW.go(repo, null, new BlockWithReturn<InheritanceCFoo2>() {
      public InheritanceCFoo2 go() {
        InheritanceCFoo2 domainObject = new InheritanceCFoo2();
        BindingMapper.toDomain(inheritanceCFoo2, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
