package features.rs.resources;

import features.domain.ParentBChildBar;
import features.domain.ParentBChildBarAlias;
import features.rs.binding.ParentBChildBarBinding;
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

@Path("/parentBChildBars")
public class ParentBChildBarResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("name") String name, final @QueryParam("parentBParent") Long parentBParent) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        ParentBChildBarAlias pbcb0 = new ParentBChildBarAlias();
        Select<ParentBChildBar> q = Select.from(pbcb0);
        if(name != null) {
          q.where(pbcb0.name.eq(name));
        }
        if(parentBParent != null) {
          q.where(pbcb0.parentBParent.eq(parentBParent));
        }
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ParentBChildBarBinding parentBChildBar) {
    return UoW.go(repo, null, new BlockWithReturn<ParentBChildBar>() {
      public ParentBChildBar go() {
        ParentBChildBar domainObject = new ParentBChildBar();
        BindingMapper.toDomain(parentBChildBar, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
