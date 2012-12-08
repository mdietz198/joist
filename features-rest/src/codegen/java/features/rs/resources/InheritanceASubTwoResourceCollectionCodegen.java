package features.rs.resources;

import features.domain.InheritanceASubTwo;
import features.domain.InheritanceASubTwoAlias;
import features.rs.binding.InheritanceASubTwoBinding;
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

@Path("/inheritanceASubTwos")
public class InheritanceASubTwoResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("name") String name, final @QueryParam("two") String two, final @QueryParam("inheritanceAOwner") Long inheritanceAOwner, final @QueryParam("inheritanceAThing") Long inheritanceAThing) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        InheritanceASubTwoAlias iast0 = new InheritanceASubTwoAlias();
        Select<InheritanceASubTwo> q = Select.from(iast0);
        if(name != null) {
          q.where(iast0.name.eq(name));
        }
        if(two != null) {
          q.where(iast0.two.eq(two));
        }
        if(inheritanceAOwner != null) {
          q.where(iast0.inheritanceAOwner.eq(inheritanceAOwner));
        }
        if(inheritanceAThing != null) {
          q.where(iast0.inheritanceAThing.eq(inheritanceAThing));
        }
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final InheritanceASubTwoBinding inheritanceASubTwo) {
    return UoW.go(repo, null, new BlockWithReturn<InheritanceASubTwo>() {
      public InheritanceASubTwo go() {
        InheritanceASubTwo domainObject = new InheritanceASubTwo();
        BindingMapper.toDomain(inheritanceASubTwo, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
