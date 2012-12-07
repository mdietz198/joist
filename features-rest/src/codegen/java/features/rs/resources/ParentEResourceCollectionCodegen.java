package features.rs.resources;

import features.domain.ParentE;
import features.domain.ParentEAlias;
import features.rs.binding.ParentEBinding;
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

@Path("/parentEs")
public class ParentEResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("name") String name, final @QueryParam("parentE") Long parentE) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        ParentEAlias pe0 = new ParentEAlias();
        Select<ParentE> q = Select.from(pe0);
        if(name != null) {
          q.where(pe0.name.eq(name));
        }
        if(parentE != null) {
          q.where(pe0.parentE.eq(parentE));
        }
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ParentEBinding parentE) {
    return UoW.go(repo, null, new BlockWithReturn<ParentE>() {
      public ParentE go() {
        ParentE domainObject = new ParentE();
        BindingMapper.toDomain(parentE, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
