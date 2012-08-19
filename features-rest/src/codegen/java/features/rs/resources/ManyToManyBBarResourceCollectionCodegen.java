package features.rs.resources;

import features.Registry;
import features.domain.ManyToManyBBar;
import features.rs.binding.ManyToManyBBarBinding;
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

@Path("/manyToManyBBars")
public class ManyToManyBBarResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, ManyToManyBBar.class, ManyToManyBBar.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final ManyToManyBBarBinding manyToManyBBar) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<ManyToManyBBar>() {
      public ManyToManyBBar go() {
        ManyToManyBBar domainObject = new ManyToManyBBar();
        BindingMapper.toDomain(manyToManyBBar, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
