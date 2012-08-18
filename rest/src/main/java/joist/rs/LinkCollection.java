package joist.rs;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import joist.domain.DomainObject;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class LinkCollection {

  private int startIndex;
  private List<Link> links = new ArrayList<Link>();

  public LinkCollection() {

  }

  public LinkCollection(int startIndex, List<? extends DomainObject> os) {
    this.startIndex = startIndex;
    for (DomainObject o : os) {
      this.links.add(new Link(o));
    }
  }

  public int getStartIndex() {
    return this.startIndex;
  }

  public void setStartIndex(int startIndex) {
    this.startIndex = startIndex;
  }

  public List<Link> getLinks() {
    return this.links;
  }

  public void setLinks(List<Link> links) {
    this.links = links;
  }

}
