package joist.rs.codegen.passes;

import joist.codegen.passes.Pass;
import joist.rs.codegen.RestCodegen;

public class OutputPass implements Pass<RestCodegen> {

  public void pass(RestCodegen codegen) {
    codegen.getOutputSourceDirectory().output();
    codegen.getOutputCodegenDirectory().output();
    if (codegen.getConfig().pruneCodegenDirectory) {
      if (codegen.getConfig().pruneInAllDirectories) {
        codegen.getOutputCodegenDirectory().pruneIfNotTouched();
      } else {
        codegen.getOutputCodegenDirectory().pruneIfNotTouchedWithinUsedPackages();
      }
    }
  }

}
