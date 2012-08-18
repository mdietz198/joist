package joist.rs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import joist.domain.DomainObject;

import org.apache.commons.lang.StringUtils;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Link {

  // TODO find better way to inject base service URL than a system property
  private static final String baseUrl = StringUtils.removeEnd(System.getProperty("baseServiceUrl"), "/");;

  private String name;
  private Long id;
  private String relativeUrl;

  public Link() {
  }

  public Link(DomainObject o) {
    this(o.getClass(), o.getId());
    this.name = o.toTextString();
  }

  public Link(Class<? extends DomainObject> type, Long id) {
    this.id = id;
    this.relativeUrl = "/" + type.getSimpleName() + "s/" + this.id;
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

  public String getUrl() {
    if (Link.baseUrl == null) {
      return null;
    }
    return Link.baseUrl + this.relativeUrl;
  }

  public void setUrl(String url) {
    if (Link.baseUrl != null) {
      this.setRelativeUrl(url.replace(Link.baseUrl, ""));
    }
  }
}
