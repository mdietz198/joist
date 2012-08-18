package features.rs.resources;

import features.Registry;
import features.domain.InheritanceC;
import features.rs.binding.InheritanceCBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/inheritanceCs/{id}")
public abstract class InheritanceCResourceCodegen {

  protected final Repository repository;

  public InheritanceCBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<InheritanceCBinding>() {
      public InheritanceCBinding go() {
        return BindingMapper.toBinding(InheritanceC.queries.find(id));
      }
    });
  }

}
