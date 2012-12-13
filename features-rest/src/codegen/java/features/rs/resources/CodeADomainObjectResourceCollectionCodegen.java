package features.rs.resources;

import com.sun.jersey.api.uri.UriBuilderImpl;
import features.domain.CodeAColor;
import features.domain.CodeADomainObject;
import features.domain.CodeADomainObjectAlias;
import features.domain.CodeASize;
import features.rs.binding.CodeADomainObjectBinding;
import features.rs.helpers.BindingMapper;
import java.util.List;
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
import joist.rs.CollectionLinkBinding;
import joist.rs.PagedCollectionBinding;

@Path("/codeADomainObjects")
public class CodeADomainObjectResourceCollectionCodegen {

  @GET
  @Produces({ "application/json", "application/xml" })
  public PagedCollectionBinding get(final @Context Repository repo, final @QueryParam("startIndex") Integer startIndexParam, final @QueryParam("maxResults") Integer maxResultsParam, final @QueryParam("name") String name, final @QueryParam("codeAColor") String codeAColor, final @QueryParam("codeASize") String codeASize) {
    return UoW.read(repo, new BlockWithReturn<PagedCollectionBinding>() {
      public PagedCollectionBinding go() {
        Integer startIndex = startIndexParam == null ? 0 : startIndexParam;
        Integer maxResults = maxResultsParam == null ? 20 : maxResultsParam;
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
        q.orderBy(cado0.id.asc());
        q.offset(startIndex);
        q.limit(maxResults );
        List<CodeADomainObject> list = q.list();
        PagedCollectionBinding result = new PagedCollectionBinding();
        result.setLinksFromDomainObjects(list);
        if (startIndex > 0) {
          result.setPrevious(new CollectionLinkBinding("previous", new UriBuilderImpl().path(CodeADomainObjectResourceCollectionCodegen.class) .queryParam("startIndex", Math.max(0, startIndex - maxResults)).queryParam("maxResults", Math.min(startIndex, maxResults)).build().toString()));
        }
        if (!list.isEmpty() && list.size() == maxResults) {
          result.setNext(new CollectionLinkBinding("next", new UriBuilderImpl().path(CodeADomainObjectResourceCollectionCodegen.class).queryParam("startIndex", startIndex + maxResults).queryParam("maxResults", maxResults).build().toString()));
        }
        return result;
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
