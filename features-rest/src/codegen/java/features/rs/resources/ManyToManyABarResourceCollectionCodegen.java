package features.rs.resources;

import features.Registry;
import features.domain.ManyToManyABar;
import features.rs.binding.ManyToManyABarBinding;
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

@Path("/manyToManyABars")
public class ManyToManyABarResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, ManyToManyABar.class, ManyToManyABar.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final ManyToManyABarBinding manyToManyABar) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<ManyToManyABar>() {
      public ManyToManyABar go() {
        ManyToManyABar domainObject = new ManyToManyABar();
        BindingMapper.toDomain(manyToManyABar, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
