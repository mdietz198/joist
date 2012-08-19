package features.rs.resources;

import features.Registry;
import features.domain.ParentDChildA;
import features.rs.binding.ParentDChildABinding;
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

@Path("/parentDChildAs")
public class ParentDChildAResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, ParentDChildA.class, ParentDChildA.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final ParentDChildABinding parentDChildA) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<ParentDChildA>() {
      public ParentDChildA go() {
        ParentDChildA domainObject = new ParentDChildA();
        BindingMapper.toDomain(parentDChildA, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
