package features.rs.resources;

import features.domain.InheritanceCFoo2;
import features.rs.binding.InheritanceCFoo2Binding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.LinkCollection;

@Path("/inheritanceCFoo2s")
public class InheritanceCFoo2ResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, InheritanceCFoo2.class, InheritanceCFoo2.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final InheritanceCFoo2Binding inheritanceCFoo2) {
    return UoW.go(repo, null, new BlockWithReturn<InheritanceCFoo2>() {
      public InheritanceCFoo2 go() {
        InheritanceCFoo2 domainObject = new InheritanceCFoo2();
        BindingMapper.toDomain(inheritanceCFoo2, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
