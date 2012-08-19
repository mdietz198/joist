package features.rs.resources;

import features.Registry;
import features.domain.InheritanceASubTwo;
import features.rs.binding.InheritanceASubTwoBinding;
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

@Path("/inheritanceASubTwos")
public class InheritanceASubTwoResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, InheritanceASubTwo.class, InheritanceASubTwo.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final InheritanceASubTwoBinding inheritanceASubTwo) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<InheritanceASubTwo>() {
      public InheritanceASubTwo go() {
        InheritanceASubTwo domainObject = new InheritanceASubTwo();
        BindingMapper.toDomain(inheritanceASubTwo, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
