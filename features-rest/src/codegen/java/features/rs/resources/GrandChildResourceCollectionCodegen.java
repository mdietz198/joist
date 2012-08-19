package features.rs.resources;

import features.domain.GrandChild;
import features.rs.binding.GrandChildBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.LinkCollection;

@Path("/grandChilds")
public class GrandChildResourceCollectionCodegen {

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get(final @Context Repository repo) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, GrandChild.class, GrandChild.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
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
