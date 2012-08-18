package features.rs.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import joist.rs.Link;
import joist.rs.LinkCollection;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ChildDto {

  public Long id;
  public String name;
  public Long version;

  public Link parent;
  public LinkCollection grandChilds;

  public ChildDto() {
  }

}
