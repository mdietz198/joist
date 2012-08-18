package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import joist.rs.Link;

@XmlRootElement(name="parentE")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParentEBinding extends Object {

  public Long id;
  public String name;
  public Long version;
  public Link parentE;

}
