package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="parentCFoo")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParentCFooBinding extends Object {

  public Long id;
  public String name;
  public Long version;

}
