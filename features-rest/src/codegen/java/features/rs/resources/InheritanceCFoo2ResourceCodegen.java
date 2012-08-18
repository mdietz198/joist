package features.rs.resources;

import features.Registry;
import features.domain.InheritanceCFoo2;
import features.rs.binding.InheritanceCFoo2Binding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/inheritanceCFoo2s/{id}")
public class InheritanceCFoo2ResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public InheritanceCFoo2Binding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<InheritanceCFoo2Binding>() {
      public InheritanceCFoo2Binding go() {
        return BindingMapper.toBinding(InheritanceCFoo2.queries.find(id));
      }
    });
  }

}
