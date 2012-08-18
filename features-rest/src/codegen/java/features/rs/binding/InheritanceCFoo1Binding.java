package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import joist.rs.Link;

@XmlRootElement(name="inheritanceCFoo1")
@XmlAccessorType(XmlAccessType.FIELD)
public class InheritanceCFoo1Binding extends InheritanceCBinding {

  public String foo;

}
