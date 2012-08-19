package features.rs.resources;

import features.domain.PrimitivesC;
import features.rs.binding.PrimitivesCBinding;
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

@Path("/primitivesCs")
public class PrimitivesCResourceCollectionCodegen {

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get(final @Context Repository repo) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, PrimitivesC.class, PrimitivesC.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
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
