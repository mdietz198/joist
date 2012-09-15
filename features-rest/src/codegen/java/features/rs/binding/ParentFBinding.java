package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import joist.rs.Link;

@XmlRootElement(name="parentF")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParentFBinding extends Object {

  public Long id;
  public String name;
  public Long version;
  public Link childOne;
  public Link childTwo;

}
