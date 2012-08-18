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
    this.config.outputRestServerCodegenDirectory = "src/codegen/java";
    GSettings.setDefaultIndentation("  ");
  }
}
