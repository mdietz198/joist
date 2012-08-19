package features.rs.resources;

import features.Registry;
import features.domain.HistoryEntry;
import features.rs.binding.HistoryEntryBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.LinkCollection;

@Path("/historyEntrys")
public class HistoryEntryResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, HistoryEntry.class, HistoryEntry.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final HistoryEntryBinding historyEntry) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<HistoryEntry>() {
      public HistoryEntry go() {
        HistoryEntry domainObject = new HistoryEntry();
        BindingMapper.toDomain(historyEntry, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
