package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import joist.rs.CollectionLinkBinding;
import joist.rs.ObjectLinkBinding;

@XmlRootElement(name="parentI")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParentIBinding extends Object {

  public Long id;
  public Long version;
  public CollectionLinkBinding childAs;
  public ObjectLinkBinding childBs;

}
