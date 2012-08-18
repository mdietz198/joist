package features.rs.domain;

import joist.rs.Link;
import joist.rs.LinkCollection;
import features.domain.Child;

public class DtoMapper {
  private DtoMapper() {
  }

  public static ChildDto toDto(Child child) {
    ChildDto dto = new ChildDto();
    dto.id = child.getId();
    dto.name = child.getName();
    dto.version = child.getVersion();
    dto.parent = new Link(child.getParent());
    dto.grandChilds = new LinkCollection(0, child.getGrandChilds());
    return dto;
  }

}
