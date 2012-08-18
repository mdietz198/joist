package features.rs.cli;

import joist.domain.orm.Db;
import joist.rs.AbstractJoistRestCli;
import joist.sourcegen.GSettings;
import joist.util.SystemProperties;

public class JoistRestCli extends AbstractJoistRestCli {

  public static final Db db = Db.MYSQL;

  static {
    if (db.isMySQL()) {
      SystemProperties.loadFromFileIfExists("./build.properties");
    } else {
      SystemProperties.loadFromFileIfExists("./build-pg.properties");
    }
  }

  public JoistRestCli() {
    super("features", db);
    this.config.outputCodegenDirectory = "src/codegen/java";
    // TODO These are copied form JoistCli. This should be shared
    this.config.setCollectionSkipped("ParentD", "parentDChildAs");
    this.config.setCollectionSkipped("ParentD", "parentDToChildCs");
    this.config.setPropertySkipped("Primitives", "skipped");
    this.config.setPropertySkipped("Primitives", "parent");
    GSettings.setDefaultIndentation("  ");
  }
}
