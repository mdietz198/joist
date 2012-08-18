package joist.rs.codegen;


/** Generates our domain objects from the database schema. */
public class Codegen extends joist.codegen.Codegen {

  public Codegen(Config config) {
    super(config);
  }

  public Config getRsConfig() {
    return (Config) this.getConfig();
  }
}
