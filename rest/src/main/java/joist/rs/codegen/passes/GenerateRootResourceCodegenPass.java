package joist.rs.codegen.passes;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import joist.codegen.dtos.Entity;
import joist.codegen.passes.Pass;
import joist.rs.CollectionLinkBinding;
import joist.rs.codegen.RestCodegen;
import joist.rs.codegen.entities.RestEntity;
import joist.sourcegen.GClass;
import joist.sourcegen.GMethod;

import com.sun.jersey.api.uri.UriBuilderImpl;

public class GenerateRootResourceCodegenPass implements Pass<RestCodegen> {

  public void pass(RestCodegen codegen) {
    GClass rootResource = codegen.getOutputCodegenDirectory().getClass(codegen.getConfig().getResourcePackage() + ".ServiceRootResource");
    rootResource.addAnnotation("@Path(\"/\")");

    GMethod getter = rootResource.getMethod("get");
    getter.returnType("List<CollectionLinkBinding>");
    getter.addAnnotation("@GET");
    getter.addAnnotation("@Produces({ \"application/xml\", \"application/json\" })");
    getter.body.line("List<CollectionLinkBinding> links = new ArrayList<CollectionLinkBinding>();");
    getter.body.line("CollectionLinkBinding link;");
    for (Entity entity : codegen.getSchema().getEntities().values()) {
      if (entity.isCodeEntity() || entity.isAbstract()) {
        continue;
      }
      RestEntity restEntity = new RestEntity(entity, codegen.getConfig());
      getter.body.line(
        "link = new CollectionLinkBinding(\"{}s\", new UriBuilderImpl().path({}.class).build().toString());",
        entity.getVariableName(),
        restEntity.getResourceCollectionClassName());
      getter.body.line("links.add(link);");
    }
    getter.body.line("return links;");
    rootResource.addImports(Path.class, GET.class, Produces.class, CollectionLinkBinding.class, List.class, ArrayList.class, UriBuilderImpl.class);
  }
}
