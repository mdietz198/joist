package features.rs.binding;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import joist.rs.PagedCollectionBinding;

@XmlRootElement(name="parentD")
@XmlAccessorType(XmlAccessType.FIELD)
public class ParentDBinding extends Object {

  public Long id;
  public String name;
  public Long version;
  public PagedCollectionBinding parentDChildBs;

}
