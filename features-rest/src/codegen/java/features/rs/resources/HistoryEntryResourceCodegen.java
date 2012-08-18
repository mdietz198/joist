package features.rs.resources;

import features.Registry;
import features.domain.HistoryEntry;
import features.rs.binding.HistoryEntryBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/historyEntrys/{id}")
public abstract class HistoryEntryResourceCodegen {

  protected final Repository repository;

  public HistoryEntryBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<HistoryEntryBinding>() {
      public HistoryEntryBinding go() {
        return BindingMapper.toBinding(HistoryEntry.queries.find(id));
      }
    });
  }

}
