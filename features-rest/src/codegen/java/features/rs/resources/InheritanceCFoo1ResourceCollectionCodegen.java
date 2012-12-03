package features.rs.resources;

import features.domain.InheritanceCFoo1;
import features.domain.InheritanceCFoo1Alias;
import features.rs.binding.InheritanceCFoo1Binding;
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

@Path("/inheritanceCFoo1s")
public class InheritanceCFoo1ResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("name") String name, final @QueryParam("foo") String foo) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        InheritanceCFoo1Alias icf0 = new InheritanceCFoo1Alias();
        Select<InheritanceCFoo1> q = Select.from(icf0);
        if(name != null) {
          q.where(icf0.name.eq(name));
        }
        if(foo != null) {
          q.where(icf0.foo.eq(foo));
        }
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final InheritanceCFoo1Binding inheritanceCFoo1) {
    return UoW.go(repo, null, new BlockWithReturn<InheritanceCFoo1>() {
      public InheritanceCFoo1 go() {
        InheritanceCFoo1 domainObject = new InheritanceCFoo1();
        BindingMapper.toDomain(inheritanceCFoo1, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
