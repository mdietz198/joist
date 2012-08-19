package features.rs.resources;

import features.Registry;
import features.domain.ValuesB;
import features.rs.binding.ValuesBBinding;
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

@Path("/valuesBs")
public class ValuesBResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, ValuesB.class, ValuesB.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final ValuesBBinding valuesB) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<ValuesB>() {
      public ValuesB go() {
        ValuesB domainObject = new ValuesB();
        BindingMapper.toDomain(valuesB, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
