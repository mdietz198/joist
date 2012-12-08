package joist.rs.codegen;

import java.util.List;

import joist.codegen.passes.Pass;
import joist.rs.codegen.passes.GenerateBindingCodegenPass;
import joist.rs.codegen.passes.GenerateBindingMapperPass;
import joist.rs.codegen.passes.GenerateCollectionResourceCodegenPass;
import joist.rs.codegen.passes.GenerateResourceCodegenPass;
import joist.rs.codegen.passes.GenerateRootResourceCodegenPass;
import joist.rs.codegen.passes.OutputPass;
import joist.util.Copy;

public class RestConfig {

  /** Where the generated-once subclasses that you add business logic go. @return E.g. <code>src/main</code> */
  public String outputSourceDirectory = "./src/main/java";
  /** Where the re-generated base classes that you do not edit go. @return E.g. <code>src/codegen</code> */
  public String outputCodegenDirectory = "./src/codegen/java";
  /** The package name of your resource classes. @return E.g. <code>app.rs.resources</code> */
  public String resourcePackage;
  /** The package name of your domain REST binding classes. @return E.g. <code>app.rs.domain</code> */
  public String bindingPackage;
  /** The package name of your generated helper classes @return E.g. <code>app.rs.helpers</code> */
  public String restHelpersPackage;
  /** Whether the codegen directory will be pruned of un-needed (to us) files. Affects only directories that contained generated classes. */
  public boolean pruneCodegenDirectory = true;
  /** Whether we should remove un-needed files even outside of the directories that immediately contain classes. Assumes joist owns the entire output directory. */
  public boolean pruneInAllDirectories = false;
  /** Maximum number of items returned from a collection by default. */
  public int defaultMaxResults = 20;

  private List<Pass<RestCodegen>> codegenPasses;

  @SuppressWarnings("unchecked")
  public RestConfig(String projectName) {
    this.setProjectNameForDefaults(projectName);
    this.codegenPasses = Copy.list(
      new GenerateResourceCodegenPass(),
      new GenerateCollectionResourceCodegenPass(),
      new GenerateRootResourceCodegenPass(),
      new GenerateBindingCodegenPass(),
      new GenerateBindingMapperPass(),
      new OutputPass());
  }

  public void setProjectNameForDefaults(String projectName) {
    this.resourcePackage = projectName + ".rs.resources";
    this.bindingPackage = projectName + ".rs.binding";
    this.restHelpersPackage = projectName + ".rs.helpers";
  }

  public List<Pass<RestCodegen>> getCodegenPasses() {
    return this.codegenPasses;
  }

  public String getOutputSourceDirectory() {
    return this.outputSourceDirectory;
  }

  public String getOutputCodegenDirectory() {
    return this.outputCodegenDirectory;
  }

  public String getResourcePackage() {
    return this.resourcePackage;
  }

  public String getBindingPackage() {
    return this.bindingPackage;
  }

  public String getRestHelpersPackage() {
    return this.restHelpersPackage;
  }
}
