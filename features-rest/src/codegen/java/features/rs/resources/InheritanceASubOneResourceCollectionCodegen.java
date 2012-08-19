package features.rs.resources;

import features.Registry;
import features.domain.InheritanceASubOne;
import features.rs.binding.InheritanceASubOneBinding;
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

@Path("/inheritanceASubOnes")
public class InheritanceASubOneResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, InheritanceASubOne.class, InheritanceASubOne.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final InheritanceASubOneBinding inheritanceASubOne) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<InheritanceASubOne>() {
      public InheritanceASubOne go() {
        InheritanceASubOne domainObject = new InheritanceASubOne();
        BindingMapper.toDomain(inheritanceASubOne, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
