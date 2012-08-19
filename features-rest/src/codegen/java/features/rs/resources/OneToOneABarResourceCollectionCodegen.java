package features.rs.resources;

import features.Registry;
import features.domain.OneToOneABar;
import features.rs.binding.OneToOneABarBinding;
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

@Path("/oneToOneABars")
public class OneToOneABarResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, OneToOneABar.class, OneToOneABar.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final OneToOneABarBinding oneToOneABar) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<OneToOneABar>() {
      public OneToOneABar go() {
        OneToOneABar domainObject = new OneToOneABar();
        BindingMapper.toDomain(oneToOneABar, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
