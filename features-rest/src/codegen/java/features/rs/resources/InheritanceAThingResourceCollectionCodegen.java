package features.rs.resources;

import features.domain.InheritanceAThing;
import features.domain.InheritanceAThingAlias;
import features.rs.binding.InheritanceAThingBinding;
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

@Path("/inheritanceAThings")
public class InheritanceAThingResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndex, final @QueryParam("maxResults") Integer maxResults, final @QueryParam("name") String name) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        InheritanceAThingAlias iat0 = new InheritanceAThingAlias();
        Select<InheritanceAThing> q = Select.from(iat0);
        if(name != null) {
          q.where(iat0.name.eq(name));
        }
        q.orderBy(iat0.id.asc());
        q.offset(startIndex == null ? 0 : startIndex);
        q.limit(maxResults == null ? 20: maxResults);
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final InheritanceAThingBinding inheritanceAThing) {
    return UoW.go(repo, null, new BlockWithReturn<InheritanceAThing>() {
      public InheritanceAThing go() {
        InheritanceAThing domainObject = new InheritanceAThing();
        BindingMapper.toDomain(inheritanceAThing, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
