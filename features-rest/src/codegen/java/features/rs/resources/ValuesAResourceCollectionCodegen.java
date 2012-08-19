package features.rs.resources;

import features.Registry;
import features.domain.ValuesA;
import features.rs.binding.ValuesABinding;
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

@Path("/valuesAs")
public class ValuesAResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, ValuesA.class, ValuesA.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final ValuesABinding valuesA) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<ValuesA>() {
      public ValuesA go() {
        ValuesA domainObject = new ValuesA();
        BindingMapper.toDomain(valuesA, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
