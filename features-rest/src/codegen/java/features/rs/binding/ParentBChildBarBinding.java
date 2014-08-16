package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import joist.rs.CollectionLinkBinding;
import joist.rs.ObjectLinkBinding;

@XmlRootElement(name="parentBChildBar")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParentBChildBarBinding extends Object {

  public Long id;
  public String name;
  public Long version;
  public ObjectLinkBinding parentBParent;
  public CollectionLinkBinding parentBChildZazs;

}
