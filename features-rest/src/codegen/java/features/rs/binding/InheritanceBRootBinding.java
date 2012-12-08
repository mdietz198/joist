package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import joist.rs.PagedCollectionBinding;

@XmlRootElement(name="inheritanceBRoot")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class InheritanceBRootBinding extends Object {

  public Long id;
  public String name;
  public Long version;
  public PagedCollectionBinding inheritanceBRootChilds;

}
