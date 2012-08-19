package features.rs.resources;

import features.Registry;
import features.domain.InheritanceBBottom;
import features.rs.binding.InheritanceBBottomBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.Block;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/inheritanceBBottoms/{id}")
public class InheritanceBBottomResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public InheritanceBBottomBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<InheritanceBBottomBinding>() {
      public InheritanceBBottomBinding go() {
        return BindingMapper.toBinding(InheritanceBBottom.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @PathParam("id") Long id, final InheritanceBBottomBinding inheritanceBBottom) {
    UoW.go(Registry.getRepository(), null, new Block() {
      public void go() {
        BindingMapper.toDomain(inheritanceBBottom, InheritanceBBottom.queries.find(id));
      }
    });
  }

}
