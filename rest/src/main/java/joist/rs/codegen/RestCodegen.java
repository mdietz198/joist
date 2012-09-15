package joist.rs.codegen;

import joist.codegen.Schema;
import joist.codegen.passes.Pass;
import joist.sourcegen.GDirectory;

public class RestCodegen {

  private final RestConfig config;
  private final Schema schema;
  private final GDirectory outputCodegenDirectory;
  private final GDirectory outputSourceDirectory;

  public RestCodegen(RestConfig config, Schema schema) {
    this.config = config;
    this.schema = schema;
    this.outputCodegenDirectory = new GDirectory(this.config.getOutputCodegenDirectory());
    this.outputSourceDirectory = new GDirectory(this.config.getOutputSourceDirectory());
  }

  public RestConfig getConfig() {
    return this.config;
  }

  public Schema getSchema() {
    return this.schema;
  }

  public GDirectory getOutputCodegenDirectory() {
    return this.outputCodegenDirectory;
  }

  public GDirectory getOutputSourceDirectory() {
    return this.outputSourceDirectory;
  }

  public void generate() {
    for (Pass<RestCodegen> pass : this.config.getCodegenPasses()) {
      pass.pass(this);
    }
  }

}
