package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="valuesA")
@XmlAccessorType(XmlAccessType.FIELD)
public class ValuesABinding extends Object {

  public String a;
  public String b;
  public Integer i;
  public Long id;
  public Integer j;
  public String name;
  public Long version;

}