package features.rs.resources;

import features.Registry;
import features.domain.ParentE;
import features.rs.binding.ParentEBinding;
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

@Path("/parentEs")
public class ParentEResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, ParentE.class, ParentE.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final ParentEBinding parentE) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<ParentE>() {
      public ParentE go() {
        ParentE domainObject = new ParentE();
        BindingMapper.toDomain(parentE, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
