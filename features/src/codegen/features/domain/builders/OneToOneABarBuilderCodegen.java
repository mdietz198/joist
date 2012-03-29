package features.domain.builders;

import features.domain.OneToOneABar;
import features.domain.OneToOneAFoo;
import joist.domain.builders.AbstractBuilder;

@SuppressWarnings("all")
public abstract class OneToOneABarBuilderCodegen extends AbstractBuilder<OneToOneABar> {

  public OneToOneABarBuilderCodegen(OneToOneABar instance) {
    super(instance);
  }

  public Long id() {
    return get().getId();
  }

  public OneToOneABarBuilder id(Long id) {
    get().setId(id);
    return (OneToOneABarBuilder) this;
  }

  public String name() {
    return get().getName();
  }

  public OneToOneABarBuilder name(String name) {
    get().setName(name);
    return (OneToOneABarBuilder) this;
  }

  public OneToOneABarBuilder with(String name) {
    return name(name);
  }

  @Override
  public OneToOneABarBuilder defaults() {
    if (name() == null) {
      name("name");
    }
    if (oneToOneAFoo() == null) {
      oneToOneAFoo(Builders.aOneToOneAFoo().defaults());
    }
    return (OneToOneABarBuilder) super.defaults();
  }

  public OneToOneAFooBuilder oneToOneAFoo() {
    if (get().getOneToOneAFoo() == null) {
      return null;
    }
    return Builders.existing(get().getOneToOneAFoo());
  }

  public OneToOneABarBuilder oneToOneAFoo(OneToOneAFoo oneToOneAFoo) {
    get().setOneToOneAFoo(oneToOneAFoo);
    return (OneToOneABarBuilder) this;
  }

  public OneToOneABarBuilder with(OneToOneAFoo oneToOneAFoo) {
    return oneToOneAFoo(oneToOneAFoo);
  }

  public OneToOneABarBuilder oneToOneAFoo(OneToOneAFooBuilder oneToOneAFoo) {
    return oneToOneAFoo(oneToOneAFoo.get());
  }

  public OneToOneABarBuilder with(OneToOneAFooBuilder oneToOneAFoo) {
    return oneToOneAFoo(oneToOneAFoo);
  }

  public OneToOneABar get() {
    return (features.domain.OneToOneABar) super.get();
  }

}
