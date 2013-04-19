package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import joist.rs.ObjectLinkBinding;

@XmlRootElement(name="childH")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChildHBinding extends Object {

  public Long id;
  public String name;
  public Long quantity;
  public Long version;
  public ObjectLinkBinding parent;

}
