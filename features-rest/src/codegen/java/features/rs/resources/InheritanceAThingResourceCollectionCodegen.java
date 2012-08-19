package features.rs.resources;

import features.Registry;
import features.domain.InheritanceAThing;
import features.rs.binding.InheritanceAThingBinding;
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

@Path("/inheritanceAThings")
public class InheritanceAThingResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, InheritanceAThing.class, InheritanceAThing.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final InheritanceAThingBinding inheritanceAThing) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<InheritanceAThing>() {
      public InheritanceAThing go() {
        InheritanceAThing domainObject = new InheritanceAThing();
        BindingMapper.toDomain(inheritanceAThing, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
