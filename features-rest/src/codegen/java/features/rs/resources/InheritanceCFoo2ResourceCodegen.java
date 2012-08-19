package features.rs.resources;

import features.domain.InheritanceCFoo2;
import features.rs.binding.InheritanceCFoo2Binding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import joist.domain.orm.Repository;
import joist.domain.uow.Block;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/inheritanceCFoo2s/{id}")
public class InheritanceCFoo2ResourceCodegen {

  @GET
  @Produces({ "application/xml" })
  public InheritanceCFoo2Binding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<InheritanceCFoo2Binding>() {
      public InheritanceCFoo2Binding go() {
        return BindingMapper.toBinding(InheritanceCFoo2.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final InheritanceCFoo2Binding inheritanceCFoo2) {
    UoW.go(repo, null, new Block() {
      public void go() {
        BindingMapper.toDomain(inheritanceCFoo2, InheritanceCFoo2.queries.find(id));
      }
    });
  }

}
