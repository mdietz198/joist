package features.rs.resources;

import features.domain.ChildG;
import features.domain.ChildGAlias;
import features.rs.binding.ChildGBinding;
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

@Path("/childGs")
public class ChildGResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndex, final @QueryParam("maxResults") Integer maxResults, final @QueryParam("name") String name, final @QueryParam("parentOne") Long parentOne, final @QueryParam("parentTwo") Long parentTwo) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
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
        q.offset(startIndex == null ? 0 : startIndex);
        q.limit(maxResults == null ? 20: maxResults);
        return new LinkCollection(0, q.list());
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
