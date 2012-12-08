package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import joist.rs.ObjectLinkBinding;

@XmlRootElement(name="manyToManyAFooToBar")
@XmlAccessorType(XmlAccessType.FIELD)
public class ManyToManyAFooToBarBinding extends Object {

  public Long id;
  public Long version;
  public ObjectLinkBinding manyToManyABar;
  public ObjectLinkBinding manyToManyAFoo;

}
