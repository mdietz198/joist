package features.rs.mappers;

import features.domain.HistoryEntry;
import features.rs.binding.HistoryEntryBinding;

public class HistoryEntryBindingMapper {

  private HistoryEntryBindingMapper() {
  }

  public static HistoryEntryBinding toBinding(HistoryEntry domainObject) {
    HistoryEntryBinding binding = new HistoryEntryBinding();
    binding.id = domainObject.getId();
    binding.newValue = domainObject.getNewValue();
    binding.oldValue = domainObject.getOldValue();
    binding.primaryKey = domainObject.getPrimaryKey();
    binding.propertyName = domainObject.getPropertyName();
    binding.rootTableName = domainObject.getRootTableName();
    binding.type = domainObject.getType();
    binding.updateTime = domainObject.getUpdateTime();
    binding.updater = domainObject.getUpdater();
    binding.version = domainObject.getVersion();
    return binding;
  }

  public static void toDomain(HistoryEntryBinding binding, HistoryEntry domainObject) {
    domainObject.setNewValue(binding.newValue);
    domainObject.setOldValue(binding.oldValue);
    domainObject.setPrimaryKey(binding.primaryKey);
    domainObject.setPropertyName(binding.propertyName);
    domainObject.setRootTableName(binding.rootTableName);
    domainObject.setType(binding.type);
    domainObject.setUpdateTime(binding.updateTime);
    domainObject.setUpdater(binding.updater);
  }

}
