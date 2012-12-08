package features.rs.resources;

import features.domain.ParentCBar;
import features.domain.ParentCBarAlias;
import features.rs.binding.ParentCBarBinding;
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

@Path("/parentCBars")
public class ParentCBarResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndex, final @QueryParam("maxResults") Integer maxResults, final @QueryParam("name") String name, final @QueryParam("firstParent") Long firstParent, final @QueryParam("secondParent") Long secondParent) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        ParentCBarAlias pcb0 = new ParentCBarAlias();
        Select<ParentCBar> q = Select.from(pcb0);
        if(name != null) {
          q.where(pcb0.name.eq(name));
        }
        if(firstParent != null) {
          q.where(pcb0.firstParent.eq(firstParent));
        }
        if(secondParent != null) {
          q.where(pcb0.secondParent.eq(secondParent));
        }
        q.orderBy(pcb0.id.asc());
        q.offset(startIndex == null ? 0 : startIndex);
        q.limit(maxResults == null ? 20: maxResults);
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ParentCBarBinding parentCBar) {
    return UoW.go(repo, null, new BlockWithReturn<ParentCBar>() {
      public ParentCBar go() {
        ParentCBar domainObject = new ParentCBar();
        BindingMapper.toDomain(parentCBar, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
