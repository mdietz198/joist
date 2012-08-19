package features.rs.resources;

import features.Registry;
import features.domain.ParentDChildC;
import features.rs.binding.ParentDChildCBinding;
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

@Path("/parentDChildCs")
public class ParentDChildCResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, ParentDChildC.class, ParentDChildC.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final ParentDChildCBinding parentDChildC) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<ParentDChildC>() {
      public ParentDChildC go() {
        ParentDChildC domainObject = new ParentDChildC();
        BindingMapper.toDomain(parentDChildC, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
