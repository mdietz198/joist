package features.rs.resources;

import features.domain.InheritanceBBottom;
import features.domain.InheritanceBBottomAlias;
import features.rs.binding.InheritanceBBottomBinding;
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

@Path("/inheritanceBBottoms")
public class InheritanceBBottomResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndex, final @QueryParam("maxResults") Integer maxResults, final @QueryParam("name") String name, final @QueryParam("middleName") String middleName, final @QueryParam("bottomName") String bottomName) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        InheritanceBBottomAlias ibb0 = new InheritanceBBottomAlias();
        Select<InheritanceBBottom> q = Select.from(ibb0);
        if(name != null) {
          q.where(ibb0.name.eq(name));
        }
        if(middleName != null) {
          q.where(ibb0.middleName.eq(middleName));
        }
        if(bottomName != null) {
          q.where(ibb0.bottomName.eq(bottomName));
        }
        q.orderBy(ibb0.id.asc());
        q.offset(startIndex == null ? 0 : startIndex);
        q.limit(maxResults == null ? 20: maxResults);
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final InheritanceBBottomBinding inheritanceBBottom) {
    return UoW.go(repo, null, new BlockWithReturn<InheritanceBBottom>() {
      public InheritanceBBottom go() {
        InheritanceBBottom domainObject = new InheritanceBBottom();
        BindingMapper.toDomain(inheritanceBBottom, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
