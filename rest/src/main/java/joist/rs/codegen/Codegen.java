package joist.rs.codegen;

import joist.sourcegen.GDirectory;

/** Generates our domain objects from the database schema. */
public class Codegen extends joist.codegen.Codegen {

  private final GDirectory outputRestServerCodegenDirectory;

  public Codegen(Config config) {
    super(config);
    this.outputRestServerCodegenDirectory = new GDirectory(config.getRestServerOutputCodegenDirectory());
  }

  public GDirectory getOutputRestServerCodegenDirectory() {
    return this.outputRestServerCodegenDirectory;
  }

  // TODO remove?
  public Config getRsConfig() {
    return (Config) this.getConfig();
  }
}
