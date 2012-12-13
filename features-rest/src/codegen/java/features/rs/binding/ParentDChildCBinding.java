package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import joist.rs.CollectionLinkBinding;

@XmlRootElement(name="parentDChildC")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParentDChildCBinding extends Object {

  public Long id;
  public String name;
  public Long version;
  public CollectionLinkBinding parentDToChildCs;
  public CollectionLinkBinding parentDs;

}
