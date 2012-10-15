package features.rs.resources;

import features.domain.PrimitivesB;
import features.rs.binding.PrimitivesBBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.LinkCollection;

@Path("/primitivesBs")
public class PrimitivesBResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, PrimitivesB.class, PrimitivesB.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final PrimitivesBBinding primitivesB) {
    return UoW.go(repo, null, new BlockWithReturn<PrimitivesB>() {
      public PrimitivesB go() {
        PrimitivesB domainObject = new PrimitivesB();
        BindingMapper.toDomain(primitivesB, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
