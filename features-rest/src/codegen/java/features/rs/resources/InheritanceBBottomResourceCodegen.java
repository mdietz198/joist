package features.rs.resources;

import features.domain.InheritanceBBottom;
import features.rs.binding.InheritanceBBottomBinding;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import joist.domain.exceptions.NotFoundException;
import joist.domain.orm.Repository;
import joist.domain.uow.Block;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.AbstractResource;
import static features.rs.mappers.InheritanceBBottomBindingMapper.toBinding;
import static features.rs.mappers.InheritanceBBottomBindingMapper.toDomain;

@Path("/inheritanceBBottoms/{id}")
public class InheritanceBBottomResourceCodegen extends AbstractResource<InheritanceBBottomBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public InheritanceBBottomBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<InheritanceBBottomBinding>() {
      public InheritanceBBottomBinding go() {
        return toBinding(InheritanceBBottom.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final InheritanceBBottomBinding inheritanceBBottom) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(inheritanceBBottom, InheritanceBBottom.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          InheritanceBBottom.queries.delete(InheritanceBBottom.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
