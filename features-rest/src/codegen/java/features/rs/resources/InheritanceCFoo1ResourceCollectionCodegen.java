package features.rs.resources;

import features.Registry;
import features.domain.InheritanceCFoo1;
import features.rs.binding.InheritanceCFoo1Binding;
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

@Path("/inheritanceCFoo1s")
public class InheritanceCFoo1ResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, InheritanceCFoo1.class, InheritanceCFoo1.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final InheritanceCFoo1Binding inheritanceCFoo1) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<InheritanceCFoo1>() {
      public InheritanceCFoo1 go() {
        InheritanceCFoo1 domainObject = new InheritanceCFoo1();
        BindingMapper.toDomain(inheritanceCFoo1, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
