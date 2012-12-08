package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import joist.rs.ObjectLinkBinding;

@XmlRootElement(name="inheritanceASubOne")
@XmlAccessorType(XmlAccessType.FIELD)
public class InheritanceASubOneBinding extends InheritanceABaseBinding {

  public String one;
  public ObjectLinkBinding inheritanceAThing;

}
