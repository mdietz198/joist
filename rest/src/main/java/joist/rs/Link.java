package joist.rs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import joist.domain.DomainObject;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Link {

  private String name;
  private Long id;
  private String relativeUrl;

  public Link() {
  }

  public Link(DomainObject o) {
    this.name = o.toTextString();
    this.id = o.getId();
    this.relativeUrl = "/" + o.getClass().getSimpleName() + "s/" + this.id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRelativeUrl() {
    return this.relativeUrl;
  }

  public void setRelativeUrl(String relativeUrl) {
    this.relativeUrl = relativeUrl;
  }

}
