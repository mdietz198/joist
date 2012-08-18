package features.rs.helpers;

import features.domain.Child;
import features.domain.CodeADomainObject;
import features.domain.GrandChild;
import features.domain.HistoryEntry;
import features.domain.InheritanceAOwner;
import features.domain.InheritanceASubOne;
import features.domain.InheritanceASubTwo;
import features.domain.InheritanceAThing;
import features.domain.InheritanceBBottom;
import features.domain.InheritanceBRootChild;
import features.domain.InheritanceCFoo1;
import features.domain.InheritanceCFoo2;
import features.domain.ManyToManyABar;
import features.domain.ManyToManyAFoo;
import features.domain.ManyToManyAFooToBar;
import features.domain.ManyToManyBBar;
import features.domain.ManyToManyBFoo;
import features.domain.ManyToManyBFooToBar;
import features.domain.OneToOneABar;
import features.domain.OneToOneAFoo;
import features.domain.OneToOneBBar;
import features.domain.OneToOneBFoo;
import features.domain.Parent;
import features.domain.ParentBChildBar;
import features.domain.ParentBChildFoo;
import features.domain.ParentBParent;
import features.domain.ParentCBar;
import features.domain.ParentCFoo;
import features.domain.ParentD;
import features.domain.ParentDChildA;
import features.domain.ParentDChildB;
import features.domain.ParentDChildC;
import features.domain.ParentDToChildC;
import features.domain.ParentE;
import features.domain.Primitives;
import features.domain.PrimitivesB;
import features.domain.PrimitivesC;
import features.domain.UserTypesAFoo;
import features.domain.ValidationAFoo;
import features.domain.ValuesA;
import features.domain.ValuesB;
import features.rs.binding.ChildBinding;
import features.rs.binding.CodeADomainObjectBinding;
import features.rs.binding.GrandChildBinding;
import features.rs.binding.HistoryEntryBinding;
import features.rs.binding.InheritanceAOwnerBinding;
import features.rs.binding.InheritanceASubOneBinding;
import features.rs.binding.InheritanceASubTwoBinding;
import features.rs.binding.InheritanceAThingBinding;
import features.rs.binding.InheritanceBBottomBinding;
import features.rs.binding.InheritanceBRootChildBinding;
import features.rs.binding.InheritanceCFoo1Binding;
import features.rs.binding.InheritanceCFoo2Binding;
import features.rs.binding.ManyToManyABarBinding;
import features.rs.binding.ManyToManyAFooBinding;
import features.rs.binding.ManyToManyAFooToBarBinding;
import features.rs.binding.ManyToManyBBarBinding;
import features.rs.binding.ManyToManyBFooBinding;
import features.rs.binding.ManyToManyBFooToBarBinding;
import features.rs.binding.OneToOneABarBinding;
import features.rs.binding.OneToOneAFooBinding;
import features.rs.binding.OneToOneBBarBinding;
import features.rs.binding.OneToOneBFooBinding;
import features.rs.binding.ParentBChildBarBinding;
import features.rs.binding.ParentBChildFooBinding;
import features.rs.binding.ParentBParentBinding;
import features.rs.binding.ParentBinding;
import features.rs.binding.ParentCBarBinding;
import features.rs.binding.ParentCFooBinding;
import features.rs.binding.ParentDBinding;
import features.rs.binding.ParentDChildABinding;
import features.rs.binding.ParentDChildBBinding;
import features.rs.binding.ParentDChildCBinding;
import features.rs.binding.ParentDToChildCBinding;
import features.rs.binding.ParentEBinding;
import features.rs.binding.PrimitivesBBinding;
import features.rs.binding.PrimitivesBinding;
import features.rs.binding.PrimitivesCBinding;
import features.rs.binding.UserTypesAFooBinding;
import features.rs.binding.ValidationAFooBinding;
import features.rs.binding.ValuesABinding;
import features.rs.binding.ValuesBBinding;

public class BindingMapper {

  private BindingMapper() {
  }

  public static ChildBinding toBinding(Child domainObject) {
    ChildBinding binding = new ChildBinding();
    return binding;
  }

  public static CodeADomainObjectBinding toBinding(CodeADomainObject domainObject) {
    CodeADomainObjectBinding binding = new CodeADomainObjectBinding();
    return binding;
  }

  public static GrandChildBinding toBinding(GrandChild domainObject) {
    GrandChildBinding binding = new GrandChildBinding();
    return binding;
  }

  public static HistoryEntryBinding toBinding(HistoryEntry domainObject) {
    HistoryEntryBinding binding = new HistoryEntryBinding();
    return binding;
  }

  public static InheritanceAOwnerBinding toBinding(InheritanceAOwner domainObject) {
    InheritanceAOwnerBinding binding = new InheritanceAOwnerBinding();
    return binding;
  }

  public static InheritanceASubOneBinding toBinding(InheritanceASubOne domainObject) {
    InheritanceASubOneBinding binding = new InheritanceASubOneBinding();
    return binding;
  }

  public static InheritanceASubTwoBinding toBinding(InheritanceASubTwo domainObject) {
    InheritanceASubTwoBinding binding = new InheritanceASubTwoBinding();
    return binding;
  }

  public static InheritanceAThingBinding toBinding(InheritanceAThing domainObject) {
    InheritanceAThingBinding binding = new InheritanceAThingBinding();
    return binding;
  }

  public static InheritanceBBottomBinding toBinding(InheritanceBBottom domainObject) {
    InheritanceBBottomBinding binding = new InheritanceBBottomBinding();
    return binding;
  }

  public static InheritanceBRootChildBinding toBinding(InheritanceBRootChild domainObject) {
    InheritanceBRootChildBinding binding = new InheritanceBRootChildBinding();
    return binding;
  }

  public static InheritanceCFoo1Binding toBinding(InheritanceCFoo1 domainObject) {
    InheritanceCFoo1Binding binding = new InheritanceCFoo1Binding();
    return binding;
  }

  public static InheritanceCFoo2Binding toBinding(InheritanceCFoo2 domainObject) {
    InheritanceCFoo2Binding binding = new InheritanceCFoo2Binding();
    return binding;
  }

  public static ManyToManyABarBinding toBinding(ManyToManyABar domainObject) {
    ManyToManyABarBinding binding = new ManyToManyABarBinding();
    return binding;
  }

  public static ManyToManyAFooBinding toBinding(ManyToManyAFoo domainObject) {
    ManyToManyAFooBinding binding = new ManyToManyAFooBinding();
    return binding;
  }

  public static ManyToManyAFooToBarBinding toBinding(ManyToManyAFooToBar domainObject) {
    ManyToManyAFooToBarBinding binding = new ManyToManyAFooToBarBinding();
    return binding;
  }

  public static ManyToManyBBarBinding toBinding(ManyToManyBBar domainObject) {
    ManyToManyBBarBinding binding = new ManyToManyBBarBinding();
    return binding;
  }

  public static ManyToManyBFooBinding toBinding(ManyToManyBFoo domainObject) {
    ManyToManyBFooBinding binding = new ManyToManyBFooBinding();
    return binding;
  }

  public static ManyToManyBFooToBarBinding toBinding(ManyToManyBFooToBar domainObject) {
    ManyToManyBFooToBarBinding binding = new ManyToManyBFooToBarBinding();
    return binding;
  }

  public static OneToOneABarBinding toBinding(OneToOneABar domainObject) {
    OneToOneABarBinding binding = new OneToOneABarBinding();
    return binding;
  }

  public static OneToOneAFooBinding toBinding(OneToOneAFoo domainObject) {
    OneToOneAFooBinding binding = new OneToOneAFooBinding();
    return binding;
  }

  public static OneToOneBBarBinding toBinding(OneToOneBBar domainObject) {
    OneToOneBBarBinding binding = new OneToOneBBarBinding();
    return binding;
  }

  public static OneToOneBFooBinding toBinding(OneToOneBFoo domainObject) {
    OneToOneBFooBinding binding = new OneToOneBFooBinding();
    return binding;
  }

  public static ParentBinding toBinding(Parent domainObject) {
    ParentBinding binding = new ParentBinding();
    return binding;
  }

  public static ParentBChildBarBinding toBinding(ParentBChildBar domainObject) {
    ParentBChildBarBinding binding = new ParentBChildBarBinding();
    return binding;
  }

  public static ParentBChildFooBinding toBinding(ParentBChildFoo domainObject) {
    ParentBChildFooBinding binding = new ParentBChildFooBinding();
    return binding;
  }

  public static ParentBParentBinding toBinding(ParentBParent domainObject) {
    ParentBParentBinding binding = new ParentBParentBinding();
    return binding;
  }

  public static ParentCBarBinding toBinding(ParentCBar domainObject) {
    ParentCBarBinding binding = new ParentCBarBinding();
    return binding;
  }

  public static ParentCFooBinding toBinding(ParentCFoo domainObject) {
    ParentCFooBinding binding = new ParentCFooBinding();
    return binding;
  }

  public static ParentDBinding toBinding(ParentD domainObject) {
    ParentDBinding binding = new ParentDBinding();
    return binding;
  }

  public static ParentDChildABinding toBinding(ParentDChildA domainObject) {
    ParentDChildABinding binding = new ParentDChildABinding();
    return binding;
  }

  public static ParentDChildBBinding toBinding(ParentDChildB domainObject) {
    ParentDChildBBinding binding = new ParentDChildBBinding();
    return binding;
  }

  public static ParentDChildCBinding toBinding(ParentDChildC domainObject) {
    ParentDChildCBinding binding = new ParentDChildCBinding();
    return binding;
  }

  public static ParentDToChildCBinding toBinding(ParentDToChildC domainObject) {
    ParentDToChildCBinding binding = new ParentDToChildCBinding();
    return binding;
  }

  public static ParentEBinding toBinding(ParentE domainObject) {
    ParentEBinding binding = new ParentEBinding();
    return binding;
  }

  public static PrimitivesBinding toBinding(Primitives domainObject) {
    PrimitivesBinding binding = new PrimitivesBinding();
    return binding;
  }

  public static PrimitivesBBinding toBinding(PrimitivesB domainObject) {
    PrimitivesBBinding binding = new PrimitivesBBinding();
    return binding;
  }

  public static PrimitivesCBinding toBinding(PrimitivesC domainObject) {
    PrimitivesCBinding binding = new PrimitivesCBinding();
    return binding;
  }

  public static UserTypesAFooBinding toBinding(UserTypesAFoo domainObject) {
    UserTypesAFooBinding binding = new UserTypesAFooBinding();
    return binding;
  }

  public static ValidationAFooBinding toBinding(ValidationAFoo domainObject) {
    ValidationAFooBinding binding = new ValidationAFooBinding();
    return binding;
  }

  public static ValuesABinding toBinding(ValuesA domainObject) {
    ValuesABinding binding = new ValuesABinding();
    return binding;
  }

  public static ValuesBBinding toBinding(ValuesB domainObject) {
    ValuesBBinding binding = new ValuesBBinding();
    return binding;
  }

}
