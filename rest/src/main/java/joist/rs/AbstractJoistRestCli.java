package joist.rs;

import joist.domain.orm.Db;
import joist.rs.codegen.Codegen;
import joist.rs.codegen.Config;
import joist.util.Inflector;

public abstract class AbstractJoistRestCli {

  public Config config;

  public AbstractJoistRestCli(String projectName, Db db) {
    this(projectName, Inflector.underscore(projectName), db);
  }

  public AbstractJoistRestCli(String projectName, String defaultDatabaseName, Db db) {
    this.config = new Config(projectName, defaultDatabaseName, db);
    if (".".equals(this.config.dbAppSaSettings.password)) {
      throw new RuntimeException("You need to set db.sa.password either on the command line or in build.properties.");
    }
  }

  public void cycle() {
    this.codegen();
  }

  public void codegen() {
    new Codegen(this.config).generate();
  }

}
