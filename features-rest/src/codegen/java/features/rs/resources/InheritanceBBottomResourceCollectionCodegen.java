package features.rs.resources;

import features.Registry;
import features.domain.InheritanceBBottom;
import features.rs.binding.InheritanceBBottomBinding;
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

@Path("/inheritanceBBottoms")
public class InheritanceBBottomResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, InheritanceBBottom.class, InheritanceBBottom.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final InheritanceBBottomBinding inheritanceBBottom) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<InheritanceBBottom>() {
      public InheritanceBBottom go() {
        InheritanceBBottom domainObject = new InheritanceBBottom();
        BindingMapper.toDomain(inheritanceBBottom, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
