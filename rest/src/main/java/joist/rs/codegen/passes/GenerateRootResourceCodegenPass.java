package joist.rs.codegen.passes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import joist.codegen.dtos.Entity;
import joist.codegen.passes.Pass;
import joist.rs.Link;
import joist.rs.LinkCollection;
import joist.sourcegen.GClass;
import joist.sourcegen.GMethod;

public class GenerateRootResourceCodegenPass implements Pass {

  public void pass(joist.codegen.Codegen c) {
    // TODO Nasty hack to get my subclassed codegen
    joist.rs.codegen.Codegen codegen = (joist.rs.codegen.Codegen) c;

    GClass rootResource = codegen.getOutputCodegenDirectory().getClass(codegen.getRsConfig().getResourcePackage() + ".ServiceRootResource");
    rootResource.addAnnotation("@Path(\"/\")");

    GMethod getter = rootResource.getMethod("get");
    getter.returnType(LinkCollection.class);
    getter.addAnnotation("@GET");
    // TODO add json
    getter.addAnnotation("@Produces({ \"application/xml\" })");
    getter.body.line("LinkCollection links = new LinkCollection();");
    getter.body.line("Link link;");
    for (Entity entity : codegen.getEntities().values()) {
      if (entity.isCodeEntity() || entity.isAbstract()) {
        continue;
      }
      getter.body.line("link = new Link();");
      getter.body.line("link.setName(\"{}s\");", entity.getVariableName());
      getter.body.line("link.setRelativeUrl(\"/{}s\");", entity.getVariableName());
      getter.body.line("links.getLinks().add(link);");
    }
    getter.body.line("return links;");
    rootResource.addImports(Path.class, GET.class, Produces.class, LinkCollection.class, Link.class);
  }
}