package features.rs.resources;

import features.domain.ParentD;
import features.domain.ParentDAlias;
import features.rs.binding.ParentDBinding;
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

@Path("/parentDs")
public class ParentDResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("name") String name) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        ParentDAlias pd0 = new ParentDAlias();
        Select<ParentD> q = Select.from(pd0);
        if(name != null) {
          q.where(pd0.name.eq(name));
        }
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ParentDBinding parentD) {
    return UoW.go(repo, null, new BlockWithReturn<ParentD>() {
      public ParentD go() {
        ParentD domainObject = new ParentD();
        BindingMapper.toDomain(parentD, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
