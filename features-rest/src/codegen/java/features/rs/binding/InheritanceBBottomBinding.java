package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="inheritanceBBottom")
@XmlAccessorType(XmlAccessType.FIELD)
public class InheritanceBBottomBinding extends InheritanceBMiddleBinding {

  public String bottomName;

}
