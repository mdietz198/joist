package features.rs.resources;

import features.domain.HistoryEntry;
import features.domain.HistoryEntryAlias;
import features.rs.binding.HistoryEntryBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import joist.domain.orm.Repository;
import joist.domain.orm.queries.Select;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.LinkCollection;

@Path("/historyEntrys")
public class HistoryEntryResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("newValue") String newValue, final @QueryParam("oldValue") String oldValue, final @QueryParam("primaryKey") Integer primaryKey, final @QueryParam("propertyName") String propertyName, final @QueryParam("rootTableName") String rootTableName, final @QueryParam("type") String type, final @QueryParam("updater") String updater) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        HistoryEntryAlias he0 = new HistoryEntryAlias();
        Select<HistoryEntry> q = Select.from(he0);
        if(newValue != null) {
          q.where(he0.newValue.eq(newValue));
        }
        if(oldValue != null) {
          q.where(he0.oldValue.eq(oldValue));
        }
        if(primaryKey != null) {
          q.where(he0.primaryKey.eq(primaryKey));
        }
        if(propertyName != null) {
          q.where(he0.propertyName.eq(propertyName));
        }
        if(rootTableName != null) {
          q.where(he0.rootTableName.eq(rootTableName));
        }
        if(type != null) {
          q.where(he0.type.eq(type));
        }
        if(updater != null) {
          q.where(he0.updater.eq(updater));
        }
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final HistoryEntryBinding historyEntry) {
    return UoW.go(repo, null, new BlockWithReturn<HistoryEntry>() {
      public HistoryEntry go() {
        HistoryEntry domainObject = new HistoryEntry();
        BindingMapper.toDomain(historyEntry, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
