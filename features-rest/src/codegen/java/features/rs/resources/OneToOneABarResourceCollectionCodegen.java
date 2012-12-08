package features.rs.resources;

import features.domain.OneToOneABar;
import features.domain.OneToOneABarAlias;
import features.rs.binding.OneToOneABarBinding;
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

@Path("/oneToOneABars")
public class OneToOneABarResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndex, final @QueryParam("maxResults") Integer maxResults, final @QueryParam("name") String name, final @QueryParam("oneToOneAFoo") Long oneToOneAFoo) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        OneToOneABarAlias otoab0 = new OneToOneABarAlias();
        Select<OneToOneABar> q = Select.from(otoab0);
        if(name != null) {
          q.where(otoab0.name.eq(name));
        }
        if(oneToOneAFoo != null) {
          q.where(otoab0.oneToOneAFoo.eq(oneToOneAFoo));
        }
        q.orderBy(otoab0.id.asc());
        q.offset(startIndex == null ? 0 : startIndex);
        q.limit(maxResults == null ? 20: maxResults);
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final OneToOneABarBinding oneToOneABar) {
    return UoW.go(repo, null, new BlockWithReturn<OneToOneABar>() {
      public OneToOneABar go() {
        OneToOneABar domainObject = new OneToOneABar();
        BindingMapper.toDomain(oneToOneABar, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
