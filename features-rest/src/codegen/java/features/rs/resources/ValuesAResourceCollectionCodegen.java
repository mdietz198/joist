package features.rs.resources;

import features.domain.ValuesA;
import features.domain.ValuesAAlias;
import features.rs.binding.ValuesABinding;
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

@Path("/valuesAs")
public class ValuesAResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("a") String a, final @QueryParam("b") String b, final @QueryParam("i") Integer i, final @QueryParam("j") Integer j, final @QueryParam("name") String name) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        ValuesAAlias va0 = new ValuesAAlias();
        Select<ValuesA> q = Select.from(va0);
        if(a != null) {
          q.where(va0.a.eq(a));
        }
        if(b != null) {
          q.where(va0.b.eq(b));
        }
        if(i != null) {
          q.where(va0.i.eq(i));
        }
        if(j != null) {
          q.where(va0.j.eq(j));
        }
        if(name != null) {
          q.where(va0.name.eq(name));
        }
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final ValuesABinding valuesA) {
    return UoW.go(repo, null, new BlockWithReturn<ValuesA>() {
      public ValuesA go() {
        ValuesA domainObject = new ValuesA();
        BindingMapper.toDomain(valuesA, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
