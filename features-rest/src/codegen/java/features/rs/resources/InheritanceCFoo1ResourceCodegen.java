package features.rs.resources;

import features.Registry;
import features.domain.InheritanceCFoo1;
import features.rs.binding.InheritanceCFoo1Binding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/inheritanceCFoo1s/{id}")
public class InheritanceCFoo1ResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public InheritanceCFoo1Binding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<InheritanceCFoo1Binding>() {
      public InheritanceCFoo1Binding go() {
        return BindingMapper.toBinding(InheritanceCFoo1.queries.find(id));
      }
    });
  }

}
