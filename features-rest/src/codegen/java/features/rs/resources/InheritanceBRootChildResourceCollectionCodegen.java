package features.rs.resources;

import features.domain.InheritanceBRootChild;
import features.domain.InheritanceBRootChildAlias;
import features.rs.binding.InheritanceBRootChildBinding;
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

@Path("/inheritanceBRootChilds")
public class InheritanceBRootChildResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("name") String name, final @QueryParam("inheritanceBRoot") Long inheritanceBRoot) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        InheritanceBRootChildAlias ibrc0 = new InheritanceBRootChildAlias();
        Select<InheritanceBRootChild> q = Select.from(ibrc0);
        if(name != null) {
          q.where(ibrc0.name.eq(name));
        }
        if(inheritanceBRoot != null) {
          q.where(ibrc0.inheritanceBRoot.eq(inheritanceBRoot));
        }
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final InheritanceBRootChildBinding inheritanceBRootChild) {
    return UoW.go(repo, null, new BlockWithReturn<InheritanceBRootChild>() {
      public InheritanceBRootChild go() {
        InheritanceBRootChild domainObject = new InheritanceBRootChild();
        BindingMapper.toDomain(inheritanceBRootChild, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
