package features.rs.resources;

import features.Registry;
import features.domain.OneToOneBFoo;
import features.rs.binding.OneToOneBFooBinding;
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

@Path("/oneToOneBFoos")
public class OneToOneBFooResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, OneToOneBFoo.class, OneToOneBFoo.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final OneToOneBFooBinding oneToOneBFoo) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<OneToOneBFoo>() {
      public OneToOneBFoo go() {
        OneToOneBFoo domainObject = new OneToOneBFoo();
        BindingMapper.toDomain(oneToOneBFoo, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
