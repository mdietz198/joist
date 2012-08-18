package joist.rs.codegen.passes;

import joist.codegen.passes.Pass;

public class OutputPass implements Pass {

  public void pass(joist.codegen.Codegen c) {
    // TODO Nasty hack to get my subclassed Codegen
    joist.rs.codegen.Codegen rsCodegen = (joist.rs.codegen.Codegen) c;
    rsCodegen.getOutputRestServerCodegenDirectory().output();
    if (rsCodegen.getConfig().pruneCodegenDirectory) {
      if (rsCodegen.getConfig().pruneInAllDirectories) {
        rsCodegen.getOutputRestServerCodegenDirectory().pruneIfNotTouched();
      } else {
        rsCodegen.getOutputRestServerCodegenDirectory().pruneIfNotTouchedWithinUsedPackages();
      }
    }
  }

}
