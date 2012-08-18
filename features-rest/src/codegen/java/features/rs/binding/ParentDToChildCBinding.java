package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import joist.rs.Link;

@XmlRootElement(name="parentDToChildC")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParentDToChildCBinding extends Object {

  public Long id;
  public Long version;
  public Link parentDChildC;
  public Link parentD;

}