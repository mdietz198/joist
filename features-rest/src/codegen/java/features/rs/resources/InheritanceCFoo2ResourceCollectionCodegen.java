package features.rs.resources;

import features.Registry;
import features.domain.InheritanceCFoo2;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.LinkCollection;

@Path("/inheritanceCFoo2s")
public class InheritanceCFoo2ResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, InheritanceCFoo2.class, InheritanceCFoo2.queries.findAllIds());
      }
    });
  }

}
