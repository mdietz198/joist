package features.rs.resources;

import features.domain.ParentDToChildC;
import features.rs.binding.ParentDToChildCBinding;
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

@Path("/parentDToChildCs/{id}")
public class ParentDToChildCResourceCodegen {

  @GET
  @Produces({ "application/xml" })
  public ParentDToChildCBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<ParentDToChildCBinding>() {
      public ParentDToChildCBinding go() {
        return BindingMapper.toBinding(ParentDToChildC.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final ParentDToChildCBinding parentDToChildC) {
    UoW.go(repo, null, new Block() {
      public void go() {
        BindingMapper.toDomain(parentDToChildC, ParentDToChildC.queries.find(id));
      }
    });
  }

}
