package joist.rs.codegen;

import java.util.List;

import joist.codegen.passes.FindCodeValuesPass;
import joist.codegen.passes.FindForeignKeysPass;
import joist.codegen.passes.FindManyToManyPropertiesPass;
import joist.codegen.passes.FindPrimitivePropertiesPass;
import joist.codegen.passes.FindTablesPass;
import joist.codegen.passes.OutputPass;
import joist.codegen.passes.Pass;
import joist.domain.orm.Db;
import joist.rs.codegen.passes.GenerateBindingCodegenPass;
import joist.rs.codegen.passes.GenerateBindingMapperPass;
import joist.rs.codegen.passes.GenerateCollectionResourceCodegenPass;
import joist.rs.codegen.passes.GenerateResourceCodegenPass;
import joist.rs.codegen.passes.GenerateRootResourceCodegenPass;
import joist.util.Copy;

public class Config extends joist.codegen.Config {

  /** The package name of your resource classes. @return E.g. <code>app.rs.resources</code> */
  public String resourcePackage;
  /** The package name of your domain REST binding classes. @return E.g. <code>app.rs.domain</code> */
  public String bindingPackage;
  /** The package name of your generated helper classes @return E.g. <code>app.rs.helpers</code> */
  public String restHelpersPackage;

  public Config(String projectName, String defaultDatabaseName, Db db) {
    super(projectName, defaultDatabaseName, db);
    // TODO remove this hack to only run my the Find passes and my Codegen pass
    this.setPasses();
  }

  public void setProjectNameForDefaults(String projectName) {
    super.setProjectNameForDefaults(projectName);
    this.resourcePackage = projectName + ".rs.resources";
    this.bindingPackage = projectName + ".rs.binding";
    this.restHelpersPackage = projectName + ".rs.helpers";
  }

  private void setPasses() {
    this.getPasses().clear();
    this.getPasses().addAll(this.getFindPasses());
    this.getPasses().add(new GenerateResourceCodegenPass());
    this.getPasses().add(new GenerateCollectionResourceCodegenPass());
    this.getPasses().add(new GenerateRootResourceCodegenPass());
    this.getPasses().add(new GenerateBindingCodegenPass());
    this.getPasses().add(new GenerateBindingMapperPass());
    this.getPasses().add(new OutputPass());
  }

  private List<Pass> getFindPasses() {
    return Copy.list(
      new FindTablesPass(),
      new FindPrimitivePropertiesPass(),
      new FindForeignKeysPass(),
      new FindCodeValuesPass(),
      new FindManyToManyPropertiesPass());
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
