package features.rs.resources;

import features.Registry;
import features.domain.InheritanceAOwner;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.LinkCollection;

@Path("/inheritanceAOwners")
public class InheritanceAOwnerResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, InheritanceAOwner.class, InheritanceAOwner.queries.findAllIds());
      }
    });
  }

}
