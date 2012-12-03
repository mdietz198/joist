package features.rs.resources;

import features.domain.ParentBChildFoo;
import features.domain.ParentBChildFooAlias;
import features.rs.binding.ParentBChildFooBinding;
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

@Path("/parentBChildFoos")
public class ParentBChildFooResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("name") String name) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        ParentBChildFooAlias pbcf0 = new ParentBChildFooAlias();
        Select<ParentBChildFoo> q = Select.from(pbcf0);
        if(name != null) {
          q.where(pbcf0.name.eq(name));
        }
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ParentBChildFooBinding parentBChildFoo) {
    return UoW.go(repo, null, new BlockWithReturn<ParentBChildFoo>() {
      public ParentBChildFoo go() {
        ParentBChildFoo domainObject = new ParentBChildFoo();
        BindingMapper.toDomain(parentBChildFoo, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
