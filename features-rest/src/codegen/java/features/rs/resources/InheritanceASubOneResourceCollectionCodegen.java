package features.rs.resources;

import features.domain.InheritanceASubOne;
import features.domain.InheritanceASubOneAlias;
import features.rs.binding.InheritanceASubOneBinding;
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

@Path("/inheritanceASubOnes")
public class InheritanceASubOneResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndex, final @QueryParam("maxResults") Integer maxResults, final @QueryParam("name") String name, final @QueryParam("one") String one, final @QueryParam("inheritanceAOwner") Long inheritanceAOwner, final @QueryParam("inheritanceAThing") Long inheritanceAThing) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        InheritanceASubOneAlias iaso0 = new InheritanceASubOneAlias();
        Select<InheritanceASubOne> q = Select.from(iaso0);
        if(name != null) {
          q.where(iaso0.name.eq(name));
        }
        if(one != null) {
          q.where(iaso0.one.eq(one));
        }
        if(inheritanceAOwner != null) {
          q.where(iaso0.inheritanceAOwner.eq(inheritanceAOwner));
        }
        if(inheritanceAThing != null) {
          q.where(iaso0.inheritanceAThing.eq(inheritanceAThing));
        }
        q.orderBy(iaso0.id.asc());
        q.offset(startIndex == null ? 0 : startIndex);
        q.limit(maxResults == null ? 20: maxResults);
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final InheritanceASubOneBinding inheritanceASubOne) {
    return UoW.go(repo, null, new BlockWithReturn<InheritanceASubOne>() {
      public InheritanceASubOne go() {
        InheritanceASubOne domainObject = new InheritanceASubOne();
        BindingMapper.toDomain(inheritanceASubOne, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
