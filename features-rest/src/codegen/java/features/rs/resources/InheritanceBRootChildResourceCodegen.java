package features.rs.resources;

import features.Registry;
import features.domain.InheritanceBRootChild;
import features.rs.binding.InheritanceBRootChildBinding;
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

@Path("/inheritanceBRootChilds/{id}")
public class InheritanceBRootChildResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public InheritanceBRootChildBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<InheritanceBRootChildBinding>() {
      public InheritanceBRootChildBinding go() {
        return BindingMapper.toBinding(InheritanceBRootChild.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @PathParam("id") Long id, final InheritanceBRootChildBinding inheritanceBRootChild) {
    UoW.go(Registry.getRepository(), null, new Block() {
      public void go() {
        BindingMapper.toDomain(inheritanceBRootChild, InheritanceBRootChild.queries.find(id));
      }
    });
  }

}
