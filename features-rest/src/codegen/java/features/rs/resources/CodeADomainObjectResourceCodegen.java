package features.rs.resources;

import features.domain.CodeADomainObject;
import features.rs.binding.CodeADomainObjectBinding;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import joist.domain.exceptions.NotFoundException;
import joist.domain.orm.Repository;
import joist.domain.uow.Block;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;
import joist.rs.AbstractResource;
import static features.rs.mappers.CodeADomainObjectBindingMapper.toBinding;
import static features.rs.mappers.CodeADomainObjectBindingMapper.toDomain;

@Path("/codeADomainObjects/{id}")
public class CodeADomainObjectResourceCodegen extends AbstractResource<CodeADomainObjectBinding> {

  @GET
  @Produces({ "application/json", "application/xml" })
  public CodeADomainObjectBinding get(final @Context Repository repo, final @PathParam("id") Long id) {
    return UoW.read(repo, new BlockWithReturn<CodeADomainObjectBinding>() {
      public CodeADomainObjectBinding go() {
        return toBinding(CodeADomainObject.queries.find(id));
      }
    });
  }

  @PUT
  @Consumes({ "application/json", "application/xml" })
  public void put(final @Context Repository repo, final @PathParam("id") Long id, final CodeADomainObjectBinding codeADomainObject) {
    UoW.go(repo, null, new Block() {
      public void go() {
        toDomain(codeADomainObject, CodeADomainObject.queries.find(id));
      }
    });
  }

  @DELETE
  public void delete(final @Context Repository repo, final @PathParam("id") Long id) {
    UoW.go(repo, null, new Block() {
      public void go() {
        try {
          CodeADomainObject.queries.delete(CodeADomainObject.queries.find(id));
        } catch (NotFoundException e) {
          // Ignore to make DELETE idempotent
        }
      }
    });
  }

}
