package joist.rs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import joist.domain.DomainObject;

import org.apache.commons.lang.StringUtils;

@XmlRootElement(name = "link")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class ObjectLinkBinding extends AbstractLinkBinding {

  private Long id;

  public ObjectLinkBinding() {
  }

  public ObjectLinkBinding(DomainObject o) {
    this(o.getClass(), o.getId());
    this.setName(o.toTextString());
  }

  public ObjectLinkBinding(Class<? extends DomainObject> type, Long id) {
    this.id = id;
    this.setRelativeUrl("/" + StringUtils.uncapitalize(type.getSimpleName()) + "s/" + this.id);
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

}
