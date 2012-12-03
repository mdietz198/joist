package features.rs.resources;

import features.domain.ParentDToChildC;
import features.domain.ParentDToChildCAlias;
import features.rs.binding.ParentDToChildCBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import joist.domain.orm.Repository;
import joist.domain.orm.queries.Select;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.LinkCollection;

@Path("/parentDToChildCs")
public class ParentDToChildCResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        ParentDToChildCAlias pdtcc0 = new ParentDToChildCAlias();
        Select<ParentDToChildC> q = Select.from(pdtcc0);
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ParentDToChildCBinding parentDToChildC) {
    return UoW.go(repo, null, new BlockWithReturn<ParentDToChildC>() {
      public ParentDToChildC go() {
        ParentDToChildC domainObject = new ParentDToChildC();
        BindingMapper.toDomain(parentDToChildC, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
