package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import joist.rs.Link;

@XmlRootElement(name="manyToManyAFooToBar")
@XmlAccessorType(XmlAccessType.FIELD)
public class ManyToManyAFooToBarBinding extends Object {

  public Long id;
  public Long version;
  public Link manyToManyABar;
  public Link manyToManyAFoo;

}
