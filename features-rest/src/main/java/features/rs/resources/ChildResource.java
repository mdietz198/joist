package features.rs.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import features.Registry;
import features.domain.Child;
import features.rs.domain.ChildDto;
import features.rs.domain.DtoMapper;

@Path("/childs/{id}")
public class ChildResource {

  @GET
  @Produces({ "application/json", "application/xml" })
  public ChildDto getChild(final @PathParam("id") Integer id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<ChildDto>() {
      public ChildDto go() {
        return DtoMapper.toDto(Child.queries.find(id));
      }
    });
  }
}
