package joist.rs;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;

import joist.domain.DomainObject;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class PagedCollectionBinding {

  private List<ObjectLinkBinding> links = new ArrayList<ObjectLinkBinding>();
  private CollectionLinkBinding previous;
  private CollectionLinkBinding next;

  public PagedCollectionBinding() {
  }

  @XmlElementRef
  public List<ObjectLinkBinding> getLinks() {
    return this.links;
  }

  public void setLinks(List<ObjectLinkBinding> links) {
    this.links = links;
  }

  public PagedCollectionBinding setLinksFromDomainObjects(List<? extends DomainObject> os) {
    this.links.clear();
    for (DomainObject o : os) {
      this.links.add(new ObjectLinkBinding(o));
    }
    return this;
  }

  public CollectionLinkBinding getPrevious() {
    return this.previous;
  }

  public void setPrevious(CollectionLinkBinding previous) {
    this.previous = previous;
  }

  public CollectionLinkBinding getNext() {
    return this.next;
  }

  public void setNext(CollectionLinkBinding next) {
    this.next = next;
  }
}
