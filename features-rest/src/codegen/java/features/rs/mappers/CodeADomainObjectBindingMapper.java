package features.rs.mappers;

import features.domain.CodeAColor;
import features.domain.CodeADomainObject;
import features.domain.CodeASize;
import features.rs.binding.CodeADomainObjectBinding;

public class CodeADomainObjectBindingMapper {

  private CodeADomainObjectBindingMapper() {
  }

  public static CodeADomainObjectBinding toBinding(CodeADomainObject domainObject) {
    CodeADomainObjectBinding binding = new CodeADomainObjectBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.codeAColor = domainObject.getCodeAColor() == null ? null : domainObject.getCodeAColor().toString();
    binding.codeASize = domainObject.getCodeASize() == null ? null : domainObject.getCodeASize().toString();
    return binding;
  }

  public static void toDomain(CodeADomainObjectBinding binding, CodeADomainObject domainObject) {
    domainObject.setName(binding.name);
    domainObject.setCodeAColor(binding.codeAColor == null ? null : CodeAColor.fromCode(binding.codeAColor));
    domainObject.setCodeASize(binding.codeASize == null ? null : CodeASize.fromCode(binding.codeASize));
  }

}
