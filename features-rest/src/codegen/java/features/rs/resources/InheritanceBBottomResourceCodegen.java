package features.rs.resources;

import features.Registry;
import features.domain.InheritanceBBottom;
import features.rs.binding.InheritanceBBottomBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/inheritanceBBottoms/{id}")
public abstract class InheritanceBBottomResourceCodegen {

  protected final Repository repository;

  public InheritanceBBottomBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<InheritanceBBottomBinding>() {
      public InheritanceBBottomBinding go() {
        return BindingMapper.toBinding(InheritanceBBottom.queries.find(id));
      }
    });
  }

}
