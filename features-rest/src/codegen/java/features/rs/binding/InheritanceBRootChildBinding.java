package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import joist.rs.Link;

@XmlRootElement(name="inheritanceBRootChild")
@XmlAccessorType(XmlAccessType.FIELD)
public class InheritanceBRootChildBinding extends Object {

  public Long id;
  public String name;
  public Long version;
  public Link inheritanceBRoot;

}
