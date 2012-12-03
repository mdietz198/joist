package features.rs.resources;

import features.domain.InheritanceASubTwo;
import features.rs.binding.InheritanceASubTwoBinding;
import features.rs.helpers.BindingMapper;
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

@Path("/inheritanceASubTwos/{id}")
public class InheritanceASubTwoResourceCodegen extends AbstractResource<InheritanceASubTwoBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public InheritanceASubTwoBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<InheritanceASubTwoBinding>() {
      public InheritanceASubTwoBinding go() {
        return BindingMapper.toBinding(InheritanceASubTwo.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final InheritanceASubTwoBinding inheritanceASubTwo) {
    UoW.go(repo, null, new Block() {
      public void go() {
        BindingMapper.toDomain(inheritanceASubTwo, InheritanceASubTwo.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          InheritanceASubTwo.queries.delete(InheritanceASubTwo.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
