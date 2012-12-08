package features.rs.resources;

import features.domain.ParentF;
import features.domain.ParentFAlias;
import features.rs.binding.ParentFBinding;
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

@Path("/parentFs")
public class ParentFResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndex, final @QueryParam("maxResults") Integer maxResults, final @QueryParam("name") String name, final @QueryParam("childOne") Long childOne, final @QueryParam("childTwo") Long childTwo) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        ParentFAlias pf0 = new ParentFAlias();
        Select<ParentF> q = Select.from(pf0);
        if(name != null) {
          q.where(pf0.name.eq(name));
        }
        if(childOne != null) {
          q.where(pf0.childOne.eq(childOne));
        }
        if(childTwo != null) {
          q.where(pf0.childTwo.eq(childTwo));
        }
        q.orderBy(pf0.id.asc());
        q.offset(startIndex == null ? 0 : startIndex);
        q.limit(maxResults == null ? 20: maxResults);
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ParentFBinding parentF) {
    return UoW.go(repo, null, new BlockWithReturn<ParentF>() {
      public ParentF go() {
        ParentF domainObject = new ParentF();
        BindingMapper.toDomain(parentF, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
