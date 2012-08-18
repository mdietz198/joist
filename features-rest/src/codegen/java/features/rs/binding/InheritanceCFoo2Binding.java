package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="inheritanceCFoo2")
@XmlAccessorType(XmlAccessType.FIELD)
public class InheritanceCFoo2Binding extends InheritanceCBinding {

  public String foo;

}
