package features.rs.resources;

import features.domain.ParentG;
import features.domain.ParentGAlias;
import features.rs.binding.ParentGBinding;
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

@Path("/parentGs")
public class ParentGResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndex, final @QueryParam("maxResults") Integer maxResults, final @QueryParam("name") String name) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        ParentGAlias pg0 = new ParentGAlias();
        Select<ParentG> q = Select.from(pg0);
        if(name != null) {
          q.where(pg0.name.eq(name));
        }
        q.orderBy(pg0.id.asc());
        q.offset(startIndex == null ? 0 : startIndex);
        q.limit(maxResults == null ? 20: maxResults);
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ParentGBinding parentG) {
    return UoW.go(repo, null, new BlockWithReturn<ParentG>() {
      public ParentG go() {
        ParentG domainObject = new ParentG();
        BindingMapper.toDomain(parentG, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
