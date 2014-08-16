package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import joist.rs.CollectionLinkBinding;

@XmlRootElement(name="parentH")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParentHBinding extends Object {

  public Long id;
  public String name;
  public Long threshold;
  public Long version;
  public CollectionLinkBinding childHs;

}
