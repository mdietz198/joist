package joist.rs;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class CollectionLinkBinding extends AbstractLinkBinding {

  public CollectionLinkBinding() {
  }

  public CollectionLinkBinding(String name, String relativeUrl) {
    this.setName(name);
    this.setRelativeUrl(relativeUrl);
  }

}
