package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import joist.rs.Link;

@XmlRootElement(name="inheritanceBMiddle")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class InheritanceBMiddleBinding extends InheritanceBRootBinding {

  public String middleName;

}
