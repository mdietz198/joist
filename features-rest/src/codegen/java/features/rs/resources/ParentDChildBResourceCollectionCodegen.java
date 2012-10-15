package features.rs.resources;

import features.domain.ParentDChildB;
import features.rs.binding.ParentDChildBBinding;
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

@Path("/parentDChildBs")
public class ParentDChildBResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, ParentDChildB.class, ParentDChildB.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ParentDChildBBinding parentDChildB) {
    return UoW.go(repo, null, new BlockWithReturn<ParentDChildB>() {
      public ParentDChildB go() {
        ParentDChildB domainObject = new ParentDChildB();
        BindingMapper.toDomain(parentDChildB, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
