package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class PrimitivesBinding extends Object {

  public Boolean flag;
  public Long id;
  public String name;
  public String skipped;
  public Long version;

}
