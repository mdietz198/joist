package features.rs.resources;

import features.domain.InheritanceCFoo2;
import features.rs.binding.InheritanceCFoo2Binding;
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
import static features.rs.mappers.InheritanceCFoo2BindingMapper.toBinding;
import static features.rs.mappers.InheritanceCFoo2BindingMapper.toDomain;

@Path("/inheritanceCFoo2s/{id}")
public class InheritanceCFoo2ResourceCodegen extends AbstractResource<InheritanceCFoo2Binding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public InheritanceCFoo2Binding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<InheritanceCFoo2Binding>() {
      public InheritanceCFoo2Binding go() {
        return toBinding(InheritanceCFoo2.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final InheritanceCFoo2Binding inheritanceCFoo2) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(inheritanceCFoo2, InheritanceCFoo2.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          InheritanceCFoo2.queries.delete(InheritanceCFoo2.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
