package features.rs.cli;

import joist.codegen.Config;
import joist.codegen.Schema;
import joist.domain.orm.Db;
import joist.rs.codegen.RestCodegen;
import joist.rs.codegen.RestConfig;
import joist.sourcegen.GSettings;
import joist.util.SystemProperties;
import features.cli.JoistCli;

public class JoistRestCli {

  public static final Db db = Db.MYSQL;

  public Config config;
  public RestConfig restConfig;

  static {
    if (db.isMySQL()) {
      SystemProperties.loadFromFileIfExists("./build.properties");
    } else {
      SystemProperties.loadFromFileIfExists("./build-pg.properties");
    }
  }

  public JoistRestCli() {
    this.config = JoistCli.config();
    this.restConfig = new RestConfig("features");

    GSettings.setDefaultIndentation("  ");
  }

  public void codegen() {
    Schema schema = new Schema(this.config);
    schema.populate();
    new RestCodegen(this.restConfig, schema).generate();
  }
}
