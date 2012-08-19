package features.rs.resources;

import features.Registry;
import features.domain.GrandChild;
import features.rs.binding.GrandChildBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.LinkCollection;

@Path("/grandChilds")
public class GrandChildResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, GrandChild.class, GrandChild.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final GrandChildBinding grandChild) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<GrandChild>() {
      public GrandChild go() {
        GrandChild domainObject = new GrandChild();
        BindingMapper.toDomain(grandChild, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
