package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import joist.rs.ObjectLinkBinding;

@XmlRootElement(name="parentBChildZaz")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParentBChildZazBinding extends Object {

  public Long id;
  public String name;
  public Long version;
  public ObjectLinkBinding parentBChildBar;
  public ObjectLinkBinding parentBParent;

}
