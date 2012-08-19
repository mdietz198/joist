package features.rs.resources;

import features.Registry;
import features.domain.ManyToManyAFoo;
import features.rs.binding.ManyToManyAFooBinding;
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

@Path("/manyToManyAFoos")
public class ManyToManyAFooResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, ManyToManyAFoo.class, ManyToManyAFoo.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final ManyToManyAFooBinding manyToManyAFoo) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<ManyToManyAFoo>() {
      public ManyToManyAFoo go() {
        ManyToManyAFoo domainObject = new ManyToManyAFoo();
        BindingMapper.toDomain(manyToManyAFoo, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
