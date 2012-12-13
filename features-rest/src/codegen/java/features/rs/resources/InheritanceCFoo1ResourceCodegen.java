package features.rs.resources;

import features.domain.InheritanceCFoo1;
import features.rs.binding.InheritanceCFoo1Binding;
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
import static features.rs.mappers.InheritanceCFoo1BindingMapper.toBinding;
import static features.rs.mappers.InheritanceCFoo1BindingMapper.toDomain;

@Path("/inheritanceCFoo1s/{id}")
public class InheritanceCFoo1ResourceCodegen extends AbstractResource<InheritanceCFoo1Binding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public InheritanceCFoo1Binding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<InheritanceCFoo1Binding>() {
      public InheritanceCFoo1Binding go() {
        return toBinding(InheritanceCFoo1.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final InheritanceCFoo1Binding inheritanceCFoo1) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(inheritanceCFoo1, InheritanceCFoo1.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          InheritanceCFoo1.queries.delete(InheritanceCFoo1.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
