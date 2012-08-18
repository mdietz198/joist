package features.rs.resources;

import features.Registry;
import features.domain.ParentDToChildC;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.LinkCollection;

@Path("/parentDToChildCs")
public class ParentDToChildCResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, ParentDToChildC.class, ParentDToChildC.queries.findAllIds());
      }
    });
  }

}
