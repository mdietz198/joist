package features.rs.resources;

import features.Registry;
import features.domain.Primitives;
import features.rs.binding.PrimitivesBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.LinkCollection;

@Path("/primitivess")
public class PrimitivesResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, Primitives.class, Primitives.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final PrimitivesBinding primitives) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<Primitives>() {
      public Primitives go() {
        Primitives domainObject = new Primitives();
        BindingMapper.toDomain(primitives, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
