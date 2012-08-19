package features.rs.resources;

import features.Registry;
import features.domain.ParentD;
import features.rs.binding.ParentDBinding;
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

@Path("/parentDs")
public class ParentDResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, ParentD.class, ParentD.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final ParentDBinding parentD) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<ParentD>() {
      public ParentD go() {
        ParentD domainObject = new ParentD();
        BindingMapper.toDomain(parentD, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
