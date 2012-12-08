package features.rs.resources;

import features.domain.Primitives;
import features.domain.PrimitivesAlias;
import features.rs.binding.PrimitivesBinding;
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

@Path("/primitivess")
public class PrimitivesResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndex, final @QueryParam("maxResults") Integer maxResults, final @QueryParam("flag") Boolean flag, final @QueryParam("name") String name) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        PrimitivesAlias p0 = new PrimitivesAlias();
        Select<Primitives> q = Select.from(p0);
        if(flag != null) {
          q.where(p0.flag.eq(flag));
        }
        if(name != null) {
          q.where(p0.name.eq(name));
        }
        q.orderBy(p0.id.asc());
        q.offset(startIndex == null ? 0 : startIndex);
        q.limit(maxResults == null ? 20: maxResults);
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final PrimitivesBinding primitives) {
    return UoW.go(repo, null, new BlockWithReturn<Primitives>() {
      public Primitives go() {
        Primitives domainObject = new Primitives();
        BindingMapper.toDomain(primitives, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
