package features.rs.resources;

import features.domain.PrimitivesB;
import features.domain.PrimitivesBAlias;
import features.rs.binding.PrimitivesBBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import joist.domain.orm.Repository;
import joist.domain.orm.queries.Select;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.LinkCollection;

@Path("/primitivesBs")
public class PrimitivesBResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("big1") Long big1, final @QueryParam("big2") Long big2, final @QueryParam("bool1") Boolean bool1, final @QueryParam("bool2") Boolean bool2, final @QueryParam("boolNullableWithDefaultFalse") Boolean boolNullableWithDefaultFalse, final @QueryParam("boolWithDefaultTrue") Boolean boolWithDefaultTrue, final @QueryParam("int1") Integer int1, final @QueryParam("int2") Integer int2, final @QueryParam("small1") Short small1, final @QueryParam("small2") Short small2) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        PrimitivesBAlias pb0 = new PrimitivesBAlias();
        Select<PrimitivesB> q = Select.from(pb0);
        if(big1 != null) {
          q.where(pb0.big1.eq(big1));
        }
        if(big2 != null) {
          q.where(pb0.big2.eq(big2));
        }
        if(bool1 != null) {
          q.where(pb0.bool1.eq(bool1));
        }
        if(bool2 != null) {
          q.where(pb0.bool2.eq(bool2));
        }
        if(boolNullableWithDefaultFalse != null) {
          q.where(pb0.boolNullableWithDefaultFalse.eq(boolNullableWithDefaultFalse));
        }
        if(boolWithDefaultTrue != null) {
          q.where(pb0.boolWithDefaultTrue.eq(boolWithDefaultTrue));
        }
        if(int1 != null) {
          q.where(pb0.int1.eq(int1));
        }
        if(int2 != null) {
          q.where(pb0.int2.eq(int2));
        }
        if(small1 != null) {
          q.where(pb0.small1.eq(small1));
        }
        if(small2 != null) {
          q.where(pb0.small2.eq(small2));
        }
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final PrimitivesBBinding primitivesB) {
    return UoW.go(repo, null, new BlockWithReturn<PrimitivesB>() {
      public PrimitivesB go() {
        PrimitivesB domainObject = new PrimitivesB();
        BindingMapper.toDomain(primitivesB, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
