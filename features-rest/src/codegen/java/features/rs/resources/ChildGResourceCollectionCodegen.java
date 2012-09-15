package features.rs.resources;

import features.domain.ChildG;
import features.rs.binding.ChildGBinding;
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

@Path("/childGs")
public class ChildGResourceCollectionCodegen {

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get(final @Context Repository repo) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, ChildG.class, ChildG.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final @Context Repository repo, final ChildGBinding childG) {
    return UoW.go(repo, null, new BlockWithReturn<ChildG>() {
      public ChildG go() {
        ChildG domainObject = new ChildG();
        BindingMapper.toDomain(childG, domainObject);
        return domainObject;
      }
    }).getId();
  }

}