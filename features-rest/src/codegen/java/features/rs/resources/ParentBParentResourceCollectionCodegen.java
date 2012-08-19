package features.rs.resources;

import features.domain.ParentBParent;
import features.rs.binding.ParentBParentBinding;
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

@Path("/parentBParents")
public class ParentBParentResourceCollectionCodegen {

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get(final @Context Repository repo) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, ParentBParent.class, ParentBParent.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final @Context Repository repo, final ParentBParentBinding parentBParent) {
    return UoW.go(repo, null, new BlockWithReturn<ParentBParent>() {
      public ParentBParent go() {
        ParentBParent domainObject = new ParentBParent();
        BindingMapper.toDomain(parentBParent, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
