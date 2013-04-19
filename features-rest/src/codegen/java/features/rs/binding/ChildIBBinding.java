package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import joist.rs.ObjectLinkBinding;

@XmlRootElement(name="childIB")
@XmlAccessorType(XmlAccessType.FIELD)
public class ChildIBBinding extends Object {

  public Long id;
  public Long version;
  public ObjectLinkBinding parent;

}
