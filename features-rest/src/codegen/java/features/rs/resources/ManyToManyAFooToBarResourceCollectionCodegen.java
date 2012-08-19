package features.rs.resources;

import features.Registry;
import features.domain.ManyToManyAFooToBar;
import features.rs.binding.ManyToManyAFooToBarBinding;
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

@Path("/manyToManyAFooToBars")
public class ManyToManyAFooToBarResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, ManyToManyAFooToBar.class, ManyToManyAFooToBar.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final ManyToManyAFooToBarBinding manyToManyAFooToBar) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<ManyToManyAFooToBar>() {
      public ManyToManyAFooToBar go() {
        ManyToManyAFooToBar domainObject = new ManyToManyAFooToBar();
        BindingMapper.toDomain(manyToManyAFooToBar, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
