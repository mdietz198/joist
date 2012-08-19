package features.rs.resources;

import features.domain.CodeADomainObject;
import features.rs.binding.CodeADomainObjectBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import joist.domain.orm.Repository;
import joist.domain.uow.Block;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/codeADomainObjects/{id}")
public class CodeADomainObjectResourceCodegen {

  @GET
  @Produces({ "application/xml" })
  public CodeADomainObjectBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<CodeADomainObjectBinding>() {
      public CodeADomainObjectBinding go() {
        return BindingMapper.toBinding(CodeADomainObject.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final CodeADomainObjectBinding codeADomainObject) {
    UoW.go(repo, null, new Block() {
      public void go() {
        BindingMapper.toDomain(codeADomainObject, CodeADomainObject.queries.find(id));
      }
    });
  }

}
