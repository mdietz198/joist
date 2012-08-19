package features.rs.resources;

import features.Registry;
import features.domain.CodeADomainObject;
import features.rs.binding.CodeADomainObjectBinding;
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

@Path("/codeADomainObjects")
public class CodeADomainObjectResourceCollectionCodegen {

  protected Repository repository;

  @GET
  @Produces({ "application/xml" })
  public LinkCollection get() {
    return UoW.read(Registry.getRepository(), new BlockWithReturn<LinkCollection>() {
      public LinkCollection go() {
        return new LinkCollection(0, CodeADomainObject.class, CodeADomainObject.queries.findAllIds());
      }
    });
  }

  @POST
  @Consumes({ "application/xml" })
  public Long post(final CodeADomainObjectBinding codeADomainObject) {
    return UoW.go(Registry.getRepository(), null, new BlockWithReturn<CodeADomainObject>() {
      public CodeADomainObject go() {
        CodeADomainObject domainObject = new CodeADomainObject();
        BindingMapper.toDomain(codeADomainObject, domainObject);
        return domainObject;
      }
    }).getId();
  }

}
