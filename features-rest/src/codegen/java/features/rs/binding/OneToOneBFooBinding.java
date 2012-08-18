package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import joist.rs.Link;

@XmlRootElement(name="oneToOneBFoo")
@XmlAccessorType(XmlAccessType.FIELD)
public class OneToOneBFooBinding extends Object {

  public Long id;
  public String name;
  public Long version;

}
