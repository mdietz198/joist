package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="primitives")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrimitivesBinding extends Object {

  public Boolean flag;
  public Long id;
  public String name;
  public Long version;

}
