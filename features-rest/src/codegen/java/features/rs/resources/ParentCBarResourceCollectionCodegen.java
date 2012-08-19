package features.rs.resources;

import features.Registry;
import features.domain.ParentCBar;
import features.rs.binding.ParentCBarBinding;
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

@Path("/parentCBars")
public class ParentCBarResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, ParentCBar.class, ParentCBar.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final ParentCBarBinding parentCBar) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<ParentCBar>() {
      public ParentCBar go() {
        ParentCBar domainObject = new ParentCBar();
        BindingMapper.toDomain(parentCBar, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
