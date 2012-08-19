package features.rs.resources;

import features.domain.InheritanceASubOne;
import features.rs.binding.InheritanceASubOneBinding;
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

@Path("/inheritanceASubOnes/{id}")
public class InheritanceASubOneResourceCodegen {

  @GET
  @Produces({ "application/xml" })
  public InheritanceASubOneBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<InheritanceASubOneBinding>() {
      public InheritanceASubOneBinding go() {
        return BindingMapper.toBinding(InheritanceASubOne.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final InheritanceASubOneBinding inheritanceASubOne) {
    UoW.go(repo, null, new Block() {
      public void go() {
        BindingMapper.toDomain(inheritanceASubOne, InheritanceASubOne.queries.find(id));
      }
    });
  }

}
