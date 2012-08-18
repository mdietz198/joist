package features.rs.binding;

import com.domainlanguage.time.TimePoint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import joist.rs.Link;

@XmlRootElement(name="historyEntry")
@XmlAccessorType(XmlAccessType.FIELD)
public class HistoryEntryBinding extends Object {

  public Long id;
  public String newValue;
  public String oldValue;
  public Integer primaryKey;
  public String propertyName;
  public String rootTableName;
  public String type;
  public TimePoint updateTime;
  public String updater;
  public Long version;

}
