package features.rs.resources;

import features.domain.GrandChild;
import features.domain.GrandChildAlias;
import features.rs.binding.GrandChildBinding;
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

@Path("/grandChilds")
public class GrandChildResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("name") String name) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        GrandChildAlias gc0 = new GrandChildAlias();
        Select<GrandChild> q = Select.from(gc0);
        if(name != null) {
          q.where(gc0.name.eq(name));
        }
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final GrandChildBinding grandChild) {
    return UoW.go(repo, null, new BlockWithReturn<GrandChild>() {
      public GrandChild go() {
        GrandChild domainObject = new GrandChild();
        BindingMapper.toDomain(grandChild, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
