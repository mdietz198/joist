package features.rs.binding;

import com.domainlanguage.money.Money;
import com.domainlanguage.time.TimePoint;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="primitivesC")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrimitivesCBinding extends Object {

  public Money dollarAmount;
  public Long id;
  public String name;
  public TimePoint timestamp;
  public Long version;

}
