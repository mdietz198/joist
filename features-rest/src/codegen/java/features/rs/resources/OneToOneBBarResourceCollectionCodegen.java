package features.rs.resources;

import features.Registry;
import features.domain.OneToOneBBar;
import features.rs.binding.OneToOneBBarBinding;
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

@Path("/oneToOneBBars")
public class OneToOneBBarResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, OneToOneBBar.class, OneToOneBBar.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final OneToOneBBarBinding oneToOneBBar) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<OneToOneBBar>() {
      public OneToOneBBar go() {
        OneToOneBBar domainObject = new OneToOneBBar();
        BindingMapper.toDomain(oneToOneBBar, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
