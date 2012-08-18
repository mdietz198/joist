package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="manyToManyBFoo")
@XmlAccessorType(XmlAccessType.FIELD)
public class ManyToManyBFooBinding extends Object {

  public Long id;
  public String name;
  public Long version;

}
