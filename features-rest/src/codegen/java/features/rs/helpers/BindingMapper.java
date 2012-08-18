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
import joist.rs.Link;
import joist.rs.LinkCollection;

public class BindingMapper {

  private BindingMapper() {
  }

  public static ChildBinding toBinding(Child domainObject) {
    ChildBinding binding = new ChildBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parent = new Link(domainObject.getParent());
    binding.grandChilds = new LinkCollection(0, domainObject.getGrandChilds());
    return binding;
  }

  public static CodeADomainObjectBinding toBinding(CodeADomainObject domainObject) {
    CodeADomainObjectBinding binding = new CodeADomainObjectBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.codeAColor = domainObject.getCodeAColor().toString();
    binding.codeASize = domainObject.getCodeASize().toString();
    return binding;
  }

  public static GrandChildBinding toBinding(GrandChild domainObject) {
    GrandChildBinding binding = new GrandChildBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.child = new Link(domainObject.getChild());
    return binding;
  }

  public static HistoryEntryBinding toBinding(HistoryEntry domainObject) {
    HistoryEntryBinding binding = new HistoryEntryBinding();
    binding.id = domainObject.getId();
    binding.newValue = domainObject.getNewValue();
    binding.oldValue = domainObject.getOldValue();
    binding.primaryKey = domainObject.getPrimaryKey();
    binding.propertyName = domainObject.getPropertyName();
    binding.rootTableName = domainObject.getRootTableName();
    binding.type = domainObject.getType();
    binding.updateTime = domainObject.getUpdateTime();
    binding.updater = domainObject.getUpdater();
    binding.version = domainObject.getVersion();
    return binding;
  }

  public static InheritanceAOwnerBinding toBinding(InheritanceAOwner domainObject) {
    InheritanceAOwnerBinding binding = new InheritanceAOwnerBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.inheritanceABases = new LinkCollection(0, domainObject.getInheritanceABases());
    return binding;
  }

  public static InheritanceASubOneBinding toBinding(InheritanceASubOne domainObject) {
    InheritanceASubOneBinding binding = new InheritanceASubOneBinding();
    binding.one = domainObject.getOne();
    binding.inheritanceAThing = new Link(domainObject.getInheritanceAThing());
    return binding;
  }

  public static InheritanceASubTwoBinding toBinding(InheritanceASubTwo domainObject) {
    InheritanceASubTwoBinding binding = new InheritanceASubTwoBinding();
    binding.two = domainObject.getTwo();
    binding.inheritanceAThing = new Link(domainObject.getInheritanceAThing());
    return binding;
  }

  public static InheritanceAThingBinding toBinding(InheritanceAThing domainObject) {
    InheritanceAThingBinding binding = new InheritanceAThingBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.inheritanceASubOnes = new LinkCollection(0, domainObject.getInheritanceASubOnes());
    binding.inheritanceASubTwos = new LinkCollection(0, domainObject.getInheritanceASubTwos());
    return binding;
  }

  public static InheritanceBBottomBinding toBinding(InheritanceBBottom domainObject) {
    InheritanceBBottomBinding binding = new InheritanceBBottomBinding();
    binding.bottomName = domainObject.getBottomName();
    return binding;
  }

  public static InheritanceBRootChildBinding toBinding(InheritanceBRootChild domainObject) {
    InheritanceBRootChildBinding binding = new InheritanceBRootChildBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.inheritanceBRoot = new Link(domainObject.getInheritanceBRoot());
    return binding;
  }

  public static InheritanceCFoo1Binding toBinding(InheritanceCFoo1 domainObject) {
    InheritanceCFoo1Binding binding = new InheritanceCFoo1Binding();
    binding.foo = domainObject.getFoo();
    return binding;
  }

  public static InheritanceCFoo2Binding toBinding(InheritanceCFoo2 domainObject) {
    InheritanceCFoo2Binding binding = new InheritanceCFoo2Binding();
    binding.foo = domainObject.getFoo();
    return binding;
  }

  public static ManyToManyABarBinding toBinding(ManyToManyABar domainObject) {
    ManyToManyABarBinding binding = new ManyToManyABarBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.manyToManyAFoos = new LinkCollection(0, domainObject.getManyToManyAFoos());
    return binding;
  }

  public static ManyToManyAFooBinding toBinding(ManyToManyAFoo domainObject) {
    ManyToManyAFooBinding binding = new ManyToManyAFooBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.manyToManyABars = new LinkCollection(0, domainObject.getManyToManyABars());
    return binding;
  }

  public static ManyToManyAFooToBarBinding toBinding(ManyToManyAFooToBar domainObject) {
    ManyToManyAFooToBarBinding binding = new ManyToManyAFooToBarBinding();
    binding.id = domainObject.getId();
    binding.version = domainObject.getVersion();
    binding.manyToManyABar = new Link(domainObject.getManyToManyABar());
    binding.manyToManyAFoo = new Link(domainObject.getManyToManyAFoo());
    return binding;
  }

  public static ManyToManyBBarBinding toBinding(ManyToManyBBar domainObject) {
    ManyToManyBBarBinding binding = new ManyToManyBBarBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.ownerManyToManyBFoos = new LinkCollection(0, domainObject.getOwnerManyToManyBFoos());
    return binding;
  }

  public static ManyToManyBFooBinding toBinding(ManyToManyBFoo domainObject) {
    ManyToManyBFooBinding binding = new ManyToManyBFooBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.owneds = new LinkCollection(0, domainObject.getOwneds());
    return binding;
  }

  public static ManyToManyBFooToBarBinding toBinding(ManyToManyBFooToBar domainObject) {
    ManyToManyBFooToBarBinding binding = new ManyToManyBFooToBarBinding();
    binding.id = domainObject.getId();
    binding.version = domainObject.getVersion();
    binding.owned = new Link(domainObject.getOwned());
    binding.ownerManyToManyBFoo = new Link(domainObject.getOwnerManyToManyBFoo());
    return binding;
  }

  public static OneToOneABarBinding toBinding(OneToOneABar domainObject) {
    OneToOneABarBinding binding = new OneToOneABarBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.oneToOneAFoo = new Link(domainObject.getOneToOneAFoo());
    return binding;
  }

  public static OneToOneAFooBinding toBinding(OneToOneAFoo domainObject) {
    OneToOneAFooBinding binding = new OneToOneAFooBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.oneToOneABars = new Link(domainObject.getOneToOneABar());
    return binding;
  }

  public static OneToOneBBarBinding toBinding(OneToOneBBar domainObject) {
    OneToOneBBarBinding binding = new OneToOneBBarBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.oneToOneBFoo = new Link(domainObject.getOneToOneBFoo());
    return binding;
  }

  public static OneToOneBFooBinding toBinding(OneToOneBFoo domainObject) {
    OneToOneBFooBinding binding = new OneToOneBFooBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.oneToOneBBars = new LinkCollection(0, domainObject.getOneToOneBBars());
    return binding;
  }

  public static ParentBinding toBinding(Parent domainObject) {
    ParentBinding binding = new ParentBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.childs = new LinkCollection(0, domainObject.getChilds());
    return binding;
  }

  public static ParentBChildBarBinding toBinding(ParentBChildBar domainObject) {
    ParentBChildBarBinding binding = new ParentBChildBarBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentBParent = new Link(domainObject.getParentBParent());
    return binding;
  }

  public static ParentBChildFooBinding toBinding(ParentBChildFoo domainObject) {
    ParentBChildFooBinding binding = new ParentBChildFooBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentBParent = new Link(domainObject.getParentBParent());
    return binding;
  }

  public static ParentBParentBinding toBinding(ParentBParent domainObject) {
    ParentBParentBinding binding = new ParentBParentBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentBChildBars = new LinkCollection(0, domainObject.getParentBChildBars());
    binding.parentBChildFoos = new LinkCollection(0, domainObject.getParentBChildFoos());
    return binding;
  }

  public static ParentCBarBinding toBinding(ParentCBar domainObject) {
    ParentCBarBinding binding = new ParentCBarBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.firstParent = new Link(domainObject.getFirstParent());
    binding.secondParent = new Link(domainObject.getSecondParent());
    return binding;
  }

  public static ParentCFooBinding toBinding(ParentCFoo domainObject) {
    ParentCFooBinding binding = new ParentCFooBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.firstParentParentCBars = new LinkCollection(0, domainObject.getFirstParentParentCBars());
    binding.secondParentParentCBars = new LinkCollection(0, domainObject.getSecondParentParentCBars());
    return binding;
  }

  public static ParentDBinding toBinding(ParentD domainObject) {
    ParentDBinding binding = new ParentDBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentDChildBs = new LinkCollection(0, domainObject.getParentDChildBs());
    return binding;
  }

  public static ParentDChildABinding toBinding(ParentDChildA domainObject) {
    ParentDChildABinding binding = new ParentDChildABinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentD = new Link(domainObject.getParentD());
    return binding;
  }

  public static ParentDChildBBinding toBinding(ParentDChildB domainObject) {
    ParentDChildBBinding binding = new ParentDChildBBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentD = new Link(domainObject.getParentD());
    return binding;
  }

  public static ParentDChildCBinding toBinding(ParentDChildC domainObject) {
    ParentDChildCBinding binding = new ParentDChildCBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentDs = new LinkCollection(0, domainObject.getParentDs());
    return binding;
  }

  public static ParentDToChildCBinding toBinding(ParentDToChildC domainObject) {
    ParentDToChildCBinding binding = new ParentDToChildCBinding();
    binding.id = domainObject.getId();
    binding.version = domainObject.getVersion();
    binding.parentDChildC = new Link(domainObject.getParentDChildC());
    binding.parentD = new Link(domainObject.getParentD());
    return binding;
  }

  public static ParentEBinding toBinding(ParentE domainObject) {
    ParentEBinding binding = new ParentEBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentE = new Link(domainObject.getParentE());
    binding.parentEs = new LinkCollection(0, domainObject.getParentEs());
    return binding;
  }

  public static PrimitivesBinding toBinding(Primitives domainObject) {
    PrimitivesBinding binding = new PrimitivesBinding();
    binding.flag = domainObject.getFlag();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    return binding;
  }

  public static PrimitivesBBinding toBinding(PrimitivesB domainObject) {
    PrimitivesBBinding binding = new PrimitivesBBinding();
    binding.big1 = domainObject.getBig1();
    binding.big2 = domainObject.getBig2();
    binding.bool1 = domainObject.getBool1();
    binding.bool2 = domainObject.getBool2();
    binding.boolNullableWithDefaultFalse = domainObject.getBoolNullableWithDefaultFalse();
    binding.boolWithDefaultTrue = domainObject.getBoolWithDefaultTrue();
    binding.id = domainObject.getId();
    binding.int1 = domainObject.getInt1();
    binding.int2 = domainObject.getInt2();
    binding.small1 = domainObject.getSmall1();
    binding.small2 = domainObject.getSmall2();
    binding.version = domainObject.getVersion();
    return binding;
  }

  public static PrimitivesCBinding toBinding(PrimitivesC domainObject) {
    PrimitivesCBinding binding = new PrimitivesCBinding();
    binding.dollarAmount = domainObject.getDollarAmount();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.timestamp = domainObject.getTimestamp();
    binding.version = domainObject.getVersion();
    return binding;
  }

  public static UserTypesAFooBinding toBinding(UserTypesAFoo domainObject) {
    UserTypesAFooBinding binding = new UserTypesAFooBinding();
    binding.created = domainObject.getCreated();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    return binding;
  }

  public static ValidationAFooBinding toBinding(ValidationAFoo domainObject) {
    ValidationAFooBinding binding = new ValidationAFooBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    return binding;
  }

  public static ValuesABinding toBinding(ValuesA domainObject) {
    ValuesABinding binding = new ValuesABinding();
    binding.a = domainObject.getA();
    binding.b = domainObject.getB();
    binding.i = domainObject.getI();
    binding.id = domainObject.getId();
    binding.j = domainObject.getJ();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    return binding;
  }

  public static ValuesBBinding toBinding(ValuesB domainObject) {
    ValuesBBinding binding = new ValuesBBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.start = domainObject.getStart();
    binding.version = domainObject.getVersion();
    return binding;
  }

}
