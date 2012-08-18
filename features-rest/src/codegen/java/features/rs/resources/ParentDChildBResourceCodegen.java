package features.rs.resources;

import features.Registry;
import features.domain.ParentDChildB;
import features.rs.binding.ParentDChildBBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/parentDChildBs/{id}")
public abstract class ParentDChildBResourceCodegen {

  protected final Repository repository;

  public ParentDChildBBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ParentDChildBBinding>() {
      public ParentDChildBBinding go() {
        return BindingMapper.toBinding(ParentDChildB.queries.find(id));
      }
    });
  }

}
