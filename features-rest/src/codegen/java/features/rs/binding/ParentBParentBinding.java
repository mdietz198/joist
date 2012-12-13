package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import joist.rs.CollectionLinkBinding;

@XmlRootElement(name="parentBParent")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParentBParentBinding extends Object {

  public Long id;
  public String name;
  public Long version;
  public CollectionLinkBinding parentBChildBars;
  public CollectionLinkBinding parentBChildFoos;

}
