package features.rs.resources;

import features.Registry;
import features.domain.ParentCFoo;
import features.rs.binding.ParentCFooBinding;
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

@Path("/parentCFoos")
public class ParentCFooResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, ParentCFoo.class, ParentCFoo.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final ParentCFooBinding parentCFoo) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<ParentCFoo>() {
      public ParentCFoo go() {
        ParentCFoo domainObject = new ParentCFoo();
        BindingMapper.toDomain(parentCFoo, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
