package joist.rs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import joist.domain.DomainObject;

import org.apache.commons.lang.StringUtils;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class CollectionLinkBinding extends AbstractLinkBinding {

  public CollectionLinkBinding() {
  }

  public CollectionLinkBinding(Class<? extends DomainObject> type, Integer startIndex, Integer maxResults) {
    this.setRelativeUrl("/" + StringUtils.uncapitalize(type.getSimpleName()) + "s?startIndex=" + startIndex + "&maxResults=" + maxResults);
  }

}
