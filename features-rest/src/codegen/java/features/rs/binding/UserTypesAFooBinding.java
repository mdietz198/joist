package features.rs.binding;

import com.domainlanguage.time.CalendarDate;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="userTypesAFoo")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserTypesAFooBinding extends Object {

  public CalendarDate created;
  public Long id;
  public String name;
  public Long version;

}
