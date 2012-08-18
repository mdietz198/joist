package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import joist.rs.Link;

@XmlRootElement(name="inheritanceASubTwo")
@XmlAccessorType(XmlAccessType.FIELD)
public class InheritanceASubTwoBinding extends InheritanceABaseBinding {

  public String two;
  public Link inheritanceAThing;

}
