package features.rs.resources;

import features.Registry;
import features.domain.CodeADomainObject;
import features.rs.binding.CodeADomainObjectBinding;
import features.rs.helpers.BindingMapper;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import joist.domain.orm.Repository;
import joist.domain.uow.BlockWithReturn;
import joist.domain.uow.UoW;

@Path("/codeADomainObjects/{id}")
public class CodeADomainObjectResourceCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public CodeADomainObjectBinding get(final @PathParam("id") Long id) {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<CodeADomainObjectBinding>() {
      public CodeADomainObjectBinding go() {
        return BindingMapper.toBinding(CodeADomainObject.queries.find(id));
      }
    });
  }

}
