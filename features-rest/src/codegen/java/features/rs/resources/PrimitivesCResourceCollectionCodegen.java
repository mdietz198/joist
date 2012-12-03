package features.rs.resources;

import features.domain.PrimitivesC;
import features.domain.PrimitivesCAlias;
import features.rs.binding.PrimitivesCBinding;
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

@Path("/primitivesCs")
public class PrimitivesCResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("name") String name) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        PrimitivesCAlias pc0 = new PrimitivesCAlias();
        Select<PrimitivesC> q = Select.from(pc0);
        if(name != null) {
          q.where(pc0.name.eq(name));
        }
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final PrimitivesCBinding primitivesC) {
    return UoW.go(repo, null, new BlockWithReturn<PrimitivesC>() {
      public PrimitivesC go() {
        PrimitivesC domainObject = new PrimitivesC();
        BindingMapper.toDomain(primitivesC, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
