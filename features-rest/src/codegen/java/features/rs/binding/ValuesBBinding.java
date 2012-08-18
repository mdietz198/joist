package features.rs.binding;

import com.domainlanguage.time.TimePoint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import joist.rs.Link;

@XmlRootElement(name="valuesB")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValuesBBinding extends Object {

  public Long id;
  public String name;
  public TimePoint start;
  public Long version;

}
