package features.rs.resources;

import features.domain.InheritanceAOwner;
import features.domain.InheritanceAOwnerAlias;
import features.rs.binding.InheritanceAOwnerBinding;
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

@Path("/inheritanceAOwners")
public class InheritanceAOwnerResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("name") String name) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        InheritanceAOwnerAlias iao0 = new InheritanceAOwnerAlias();
        Select<InheritanceAOwner> q = Select.from(iao0);
        if(name != null) {
          q.where(iao0.name.eq(name));
        }
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final InheritanceAOwnerBinding inheritanceAOwner) {
    return UoW.go(repo, null, new BlockWithReturn<InheritanceAOwner>() {
      public InheritanceAOwner go() {
        InheritanceAOwner domainObject = new InheritanceAOwner();
        BindingMapper.toDomain(inheritanceAOwner, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
