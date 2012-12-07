package features.rs.resources;

import features.domain.CodeAColor;
import features.domain.CodeADomainObject;
import features.domain.CodeADomainObjectAlias;
import features.domain.CodeASize;
import features.rs.binding.CodeADomainObjectBinding;
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

@Path("/codeADomainObjects")
public class CodeADomainObjectResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public LinkCollection get(final @Context Repository repo, final @QueryParam("name") String name, final @QueryParam("codeAColor") String codeAColor, final @QueryParam("codeASize") String codeASize) {
    return UoW.read(repo, new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        CodeADomainObjectAlias cado0 = new CodeADomainObjectAlias();
        Select<CodeADomainObject> q = Select.from(cado0);
        if(name != null) {
          q.where(cado0.name.eq(name));
        }
        if(codeAColor != null) {
          q.where(cado0.codeAColor.eq(CodeAColor.fromCode(codeAColor)));
        }
        if(codeASize != null) {
          q.where(cado0.codeASize.eq(CodeASize.fromCode(codeASize)));
        }
        return new LinkCollection(0, q.list());
      }
    });
  }

  @POST
  @Consumes({ "application/json", "application/xml" })
  public Long post(final @Context Repository repo, final CodeADomainObjectBinding codeADomainObject) {
    return UoW.go(repo, null, new BlockWithReturn<CodeADomainObject>() {
      public CodeADomainObject go() {
        CodeADomainObject domainObject = new CodeADomainObject();
        BindingMapper.toDomain(codeADomainObject, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
