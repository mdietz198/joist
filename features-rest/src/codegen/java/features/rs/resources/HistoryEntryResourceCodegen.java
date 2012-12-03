package features.rs.resources;

import features.domain.HistoryEntry;
import features.rs.binding.HistoryEntryBinding;
import features.rs.helpers.BindingMapper;
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

@Path("/historyEntrys/{id}")
public class HistoryEntryResourceCodegen extends AbstractResource<HistoryEntryBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public HistoryEntryBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<HistoryEntryBinding>() {
      public HistoryEntryBinding go() {
        return BindingMapper.toBinding(HistoryEntry.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final HistoryEntryBinding historyEntry) {
    UoW.go(repo, null, new Block() {
      public void go() {
        BindingMapper.toDomain(historyEntry, HistoryEntry.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          HistoryEntry.queries.delete(HistoryEntry.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
