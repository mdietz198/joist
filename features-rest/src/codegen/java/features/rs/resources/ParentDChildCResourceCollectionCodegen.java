package features.rs.resources;

import features.domain.ParentDChildC;
import features.domain.ParentDChildCAlias;
import features.rs.binding.ParentDChildCBinding;
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

@Path("/parentDChildCs")
public class ParentDChildCResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("name") String name) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        ParentDChildCAlias pdcc0 = new ParentDChildCAlias();
        Select<ParentDChildC> q = Select.from(pdcc0);
        if(name != null) {
          q.where(pdcc0.name.eq(name));
        }
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ParentDChildCBinding parentDChildC) {
    return UoW.go(repo, null, new BlockWithReturn<ParentDChildC>() {
      public ParentDChildC go() {
        ParentDChildC domainObject = new ParentDChildC();
        BindingMapper.toDomain(parentDChildC, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
