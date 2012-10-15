package features.rs.resources;

import features.domain.ParentF;
import features.rs.binding.ParentFBinding;
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

@Path("/parentFs")
public class ParentFResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, ParentF.class, ParentF.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ParentFBinding parentF) {
    return UoW.go(repo, null, new BlockWithReturn<ParentF>() {
      public ParentF go() {
        ParentF domainObject = new ParentF();
        BindingMapper.toDomain(parentF, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
