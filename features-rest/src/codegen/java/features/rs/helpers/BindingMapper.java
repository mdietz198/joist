package features.rs.helpers;

import features.domain.Child;
import features.domain.CodeAColor;
import features.domain.CodeADomainObject;
import features.domain.CodeASize;
import features.domain.GrandChild;
import features.domain.HistoryEntry;
import features.domain.InheritanceABase;
import features.domain.InheritanceAOwner;
import features.domain.InheritanceASubOne;
import features.domain.InheritanceASubTwo;
import features.domain.InheritanceAThing;
import features.domain.InheritanceBBottom;
import features.domain.InheritanceBRoot;
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
import java.util.ArrayList;
import java.util.List;
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
    binding.parent = domainObject.getParent() == null ? null : new Link(domainObject.getParent());
    binding.grandChilds = domainObject.getGrandChilds() == null ? null : new LinkCollection(0, domainObject.getGrandChilds());
    return binding;
  }

  public static void toDomain(ChildBinding binding, Child domainObject) {
    domainObject.setName(binding.name);
    domainObject.setParent(binding.parent == null ? null : binding.parent.getId() == null ? null : Parent.queries.find(binding.parent.getId()));
    final List<GrandChild> grandChilds = new ArrayList<GrandChild>();
    for (final Link l : binding.grandChilds.getLinks()) {
      GrandChild o = l.getId() == null ? null : GrandChild.queries.find(l.getId());
      grandChilds.add(o);
    }
    domainObject.setGrandChilds(grandChilds);
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

  public static GrandChildBinding toBinding(GrandChild domainObject) {
    GrandChildBinding binding = new GrandChildBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.child = domainObject.getChild() == null ? null : new Link(domainObject.getChild());
    return binding;
  }

  public static void toDomain(GrandChildBinding binding, GrandChild domainObject) {
    domainObject.setName(binding.name);
    domainObject.setChild(binding.child == null ? null : binding.child.getId() == null ? null : Child.queries.find(binding.child.getId()));
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

  public static void toDomain(HistoryEntryBinding binding, HistoryEntry domainObject) {
    domainObject.setNewValue(binding.newValue);
    domainObject.setOldValue(binding.oldValue);
    domainObject.setPrimaryKey(binding.primaryKey);
    domainObject.setPropertyName(binding.propertyName);
    domainObject.setRootTableName(binding.rootTableName);
    domainObject.setType(binding.type);
    domainObject.setUpdateTime(binding.updateTime);
    domainObject.setUpdater(binding.updater);
  }

  public static InheritanceAOwnerBinding toBinding(InheritanceAOwner domainObject) {
    InheritanceAOwnerBinding binding = new InheritanceAOwnerBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.inheritanceABases = domainObject.getInheritanceABases() == null ? null : new LinkCollection(0, domainObject.getInheritanceABases());
    return binding;
  }

  public static void toDomain(InheritanceAOwnerBinding binding, InheritanceAOwner domainObject) {
    domainObject.setName(binding.name);
    final List<InheritanceABase> inheritanceABases = new ArrayList<InheritanceABase>();
    for (final Link l : binding.inheritanceABases.getLinks()) {
      InheritanceABase o = l.getId() == null ? null : InheritanceABase.queries.find(l.getId());
      inheritanceABases.add(o);
    }
    domainObject.setInheritanceABases(inheritanceABases);
  }

  public static InheritanceASubOneBinding toBinding(InheritanceASubOne domainObject) {
    InheritanceASubOneBinding binding = new InheritanceASubOneBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.one = domainObject.getOne();
    binding.inheritanceAOwner = domainObject.getInheritanceAOwner() == null ? null : new Link(domainObject.getInheritanceAOwner());
    binding.inheritanceAThing = domainObject.getInheritanceAThing() == null ? null : new Link(domainObject.getInheritanceAThing());
    return binding;
  }

  public static void toDomain(InheritanceASubOneBinding binding, InheritanceASubOne domainObject) {
    domainObject.setName(binding.name);
    domainObject.setOne(binding.one);
    domainObject.setInheritanceAOwner(binding.inheritanceAOwner == null ? null : binding.inheritanceAOwner.getId() == null ? null : InheritanceAOwner.queries.find(binding.inheritanceAOwner.getId()));
    domainObject.setInheritanceAThing(binding.inheritanceAThing == null ? null : binding.inheritanceAThing.getId() == null ? null : InheritanceAThing.queries.find(binding.inheritanceAThing.getId()));
  }

  public static InheritanceASubTwoBinding toBinding(InheritanceASubTwo domainObject) {
    InheritanceASubTwoBinding binding = new InheritanceASubTwoBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.two = domainObject.getTwo();
    binding.inheritanceAOwner = domainObject.getInheritanceAOwner() == null ? null : new Link(domainObject.getInheritanceAOwner());
    binding.inheritanceAThing = domainObject.getInheritanceAThing() == null ? null : new Link(domainObject.getInheritanceAThing());
    return binding;
  }

  public static void toDomain(InheritanceASubTwoBinding binding, InheritanceASubTwo domainObject) {
    domainObject.setName(binding.name);
    domainObject.setTwo(binding.two);
    domainObject.setInheritanceAOwner(binding.inheritanceAOwner == null ? null : binding.inheritanceAOwner.getId() == null ? null : InheritanceAOwner.queries.find(binding.inheritanceAOwner.getId()));
    domainObject.setInheritanceAThing(binding.inheritanceAThing == null ? null : binding.inheritanceAThing.getId() == null ? null : InheritanceAThing.queries.find(binding.inheritanceAThing.getId()));
  }

  public static InheritanceAThingBinding toBinding(InheritanceAThing domainObject) {
    InheritanceAThingBinding binding = new InheritanceAThingBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.inheritanceASubOnes = domainObject.getInheritanceASubOnes() == null ? null : new LinkCollection(0, domainObject.getInheritanceASubOnes());
    binding.inheritanceASubTwos = domainObject.getInheritanceASubTwos() == null ? null : new LinkCollection(0, domainObject.getInheritanceASubTwos());
    return binding;
  }

  public static void toDomain(InheritanceAThingBinding binding, InheritanceAThing domainObject) {
    domainObject.setName(binding.name);
    final List<InheritanceASubOne> inheritanceASubOnes = new ArrayList<InheritanceASubOne>();
    for (final Link l : binding.inheritanceASubOnes.getLinks()) {
      InheritanceASubOne o = l.getId() == null ? null : InheritanceASubOne.queries.find(l.getId());
      inheritanceASubOnes.add(o);
    }
    domainObject.setInheritanceASubOnes(inheritanceASubOnes);
    final List<InheritanceASubTwo> inheritanceASubTwos = new ArrayList<InheritanceASubTwo>();
    for (final Link l : binding.inheritanceASubTwos.getLinks()) {
      InheritanceASubTwo o = l.getId() == null ? null : InheritanceASubTwo.queries.find(l.getId());
      inheritanceASubTwos.add(o);
    }
    domainObject.setInheritanceASubTwos(inheritanceASubTwos);
  }

  public static InheritanceBBottomBinding toBinding(InheritanceBBottom domainObject) {
    InheritanceBBottomBinding binding = new InheritanceBBottomBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.middleName = domainObject.getMiddleName();
    binding.bottomName = domainObject.getBottomName();
    binding.inheritanceBRootChilds = domainObject.getInheritanceBRootChilds() == null ? null : new LinkCollection(0, domainObject.getInheritanceBRootChilds());
    return binding;
  }

  public static void toDomain(InheritanceBBottomBinding binding, InheritanceBBottom domainObject) {
    domainObject.setName(binding.name);
    domainObject.setMiddleName(binding.middleName);
    domainObject.setBottomName(binding.bottomName);
    final List<InheritanceBRootChild> inheritanceBRootChilds = new ArrayList<InheritanceBRootChild>();
    for (final Link l : binding.inheritanceBRootChilds.getLinks()) {
      InheritanceBRootChild o = l.getId() == null ? null : InheritanceBRootChild.queries.find(l.getId());
      inheritanceBRootChilds.add(o);
    }
    domainObject.setInheritanceBRootChilds(inheritanceBRootChilds);
  }

  public static InheritanceBRootChildBinding toBinding(InheritanceBRootChild domainObject) {
    InheritanceBRootChildBinding binding = new InheritanceBRootChildBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.inheritanceBRoot = domainObject.getInheritanceBRoot() == null ? null : new Link(domainObject.getInheritanceBRoot());
    return binding;
  }

  public static void toDomain(InheritanceBRootChildBinding binding, InheritanceBRootChild domainObject) {
    domainObject.setName(binding.name);
    domainObject.setInheritanceBRoot(binding.inheritanceBRoot == null ? null : binding.inheritanceBRoot.getId() == null ? null : InheritanceBRoot.queries.find(binding.inheritanceBRoot.getId()));
  }

  public static InheritanceCFoo1Binding toBinding(InheritanceCFoo1 domainObject) {
    InheritanceCFoo1Binding binding = new InheritanceCFoo1Binding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.foo = domainObject.getFoo();
    return binding;
  }

  public static void toDomain(InheritanceCFoo1Binding binding, InheritanceCFoo1 domainObject) {
    domainObject.setName(binding.name);
    domainObject.setFoo(binding.foo);
  }

  public static InheritanceCFoo2Binding toBinding(InheritanceCFoo2 domainObject) {
    InheritanceCFoo2Binding binding = new InheritanceCFoo2Binding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.foo = domainObject.getFoo();
    return binding;
  }

  public static void toDomain(InheritanceCFoo2Binding binding, InheritanceCFoo2 domainObject) {
    domainObject.setName(binding.name);
    domainObject.setFoo(binding.foo);
  }

  public static ManyToManyABarBinding toBinding(ManyToManyABar domainObject) {
    ManyToManyABarBinding binding = new ManyToManyABarBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.manyToManyAFoos = domainObject.getManyToManyAFoos() == null ? null : new LinkCollection(0, domainObject.getManyToManyAFoos());
    return binding;
  }

  public static void toDomain(ManyToManyABarBinding binding, ManyToManyABar domainObject) {
    domainObject.setName(binding.name);
    final List<ManyToManyAFoo> manyToManyAFoos = new ArrayList<ManyToManyAFoo>();
    for (final Link l : binding.manyToManyAFoos.getLinks()) {
      ManyToManyAFoo o = l.getId() == null ? null : ManyToManyAFoo.queries.find(l.getId());
      manyToManyAFoos.add(o);
    }
    domainObject.setManyToManyAFoos(manyToManyAFoos);
  }

  public static ManyToManyAFooBinding toBinding(ManyToManyAFoo domainObject) {
    ManyToManyAFooBinding binding = new ManyToManyAFooBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.manyToManyABars = domainObject.getManyToManyABars() == null ? null : new LinkCollection(0, domainObject.getManyToManyABars());
    return binding;
  }

  public static void toDomain(ManyToManyAFooBinding binding, ManyToManyAFoo domainObject) {
    domainObject.setName(binding.name);
    final List<ManyToManyABar> manyToManyABars = new ArrayList<ManyToManyABar>();
    for (final Link l : binding.manyToManyABars.getLinks()) {
      ManyToManyABar o = l.getId() == null ? null : ManyToManyABar.queries.find(l.getId());
      manyToManyABars.add(o);
    }
    domainObject.setManyToManyABars(manyToManyABars);
  }

  public static ManyToManyAFooToBarBinding toBinding(ManyToManyAFooToBar domainObject) {
    ManyToManyAFooToBarBinding binding = new ManyToManyAFooToBarBinding();
    binding.id = domainObject.getId();
    binding.version = domainObject.getVersion();
    binding.manyToManyABar = domainObject.getManyToManyABar() == null ? null : new Link(domainObject.getManyToManyABar());
    binding.manyToManyAFoo = domainObject.getManyToManyAFoo() == null ? null : new Link(domainObject.getManyToManyAFoo());
    return binding;
  }

  public static void toDomain(ManyToManyAFooToBarBinding binding, ManyToManyAFooToBar domainObject) {
    domainObject.setManyToManyABar(binding.manyToManyABar == null ? null : binding.manyToManyABar.getId() == null ? null : ManyToManyABar.queries.find(binding.manyToManyABar.getId()));
    domainObject.setManyToManyAFoo(binding.manyToManyAFoo == null ? null : binding.manyToManyAFoo.getId() == null ? null : ManyToManyAFoo.queries.find(binding.manyToManyAFoo.getId()));
  }

  public static ManyToManyBBarBinding toBinding(ManyToManyBBar domainObject) {
    ManyToManyBBarBinding binding = new ManyToManyBBarBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.ownerManyToManyBFoos = domainObject.getOwnerManyToManyBFoos() == null ? null : new LinkCollection(0, domainObject.getOwnerManyToManyBFoos());
    return binding;
  }

  public static void toDomain(ManyToManyBBarBinding binding, ManyToManyBBar domainObject) {
    domainObject.setName(binding.name);
    final List<ManyToManyBFoo> ownerManyToManyBFoos = new ArrayList<ManyToManyBFoo>();
    for (final Link l : binding.ownerManyToManyBFoos.getLinks()) {
      ManyToManyBFoo o = l.getId() == null ? null : ManyToManyBFoo.queries.find(l.getId());
      ownerManyToManyBFoos.add(o);
    }
    domainObject.setOwnerManyToManyBFoos(ownerManyToManyBFoos);
  }

  public static ManyToManyBFooBinding toBinding(ManyToManyBFoo domainObject) {
    ManyToManyBFooBinding binding = new ManyToManyBFooBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.owneds = domainObject.getOwneds() == null ? null : new LinkCollection(0, domainObject.getOwneds());
    return binding;
  }

  public static void toDomain(ManyToManyBFooBinding binding, ManyToManyBFoo domainObject) {
    domainObject.setName(binding.name);
    final List<ManyToManyBBar> owneds = new ArrayList<ManyToManyBBar>();
    for (final Link l : binding.owneds.getLinks()) {
      ManyToManyBBar o = l.getId() == null ? null : ManyToManyBBar.queries.find(l.getId());
      owneds.add(o);
    }
    domainObject.setOwneds(owneds);
  }

  public static ManyToManyBFooToBarBinding toBinding(ManyToManyBFooToBar domainObject) {
    ManyToManyBFooToBarBinding binding = new ManyToManyBFooToBarBinding();
    binding.id = domainObject.getId();
    binding.version = domainObject.getVersion();
    binding.owned = domainObject.getOwned() == null ? null : new Link(domainObject.getOwned());
    binding.ownerManyToManyBFoo = domainObject.getOwnerManyToManyBFoo() == null ? null : new Link(domainObject.getOwnerManyToManyBFoo());
    return binding;
  }

  public static void toDomain(ManyToManyBFooToBarBinding binding, ManyToManyBFooToBar domainObject) {
    domainObject.setOwned(binding.owned == null ? null : binding.owned.getId() == null ? null : ManyToManyBBar.queries.find(binding.owned.getId()));
    domainObject.setOwnerManyToManyBFoo(binding.ownerManyToManyBFoo == null ? null : binding.ownerManyToManyBFoo.getId() == null ? null : ManyToManyBFoo.queries.find(binding.ownerManyToManyBFoo.getId()));
  }

  public static OneToOneABarBinding toBinding(OneToOneABar domainObject) {
    OneToOneABarBinding binding = new OneToOneABarBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.oneToOneAFoo = domainObject.getOneToOneAFoo() == null ? null : new Link(domainObject.getOneToOneAFoo());
    return binding;
  }

  public static void toDomain(OneToOneABarBinding binding, OneToOneABar domainObject) {
    domainObject.setName(binding.name);
    domainObject.setOneToOneAFoo(binding.oneToOneAFoo == null ? null : binding.oneToOneAFoo.getId() == null ? null : OneToOneAFoo.queries.find(binding.oneToOneAFoo.getId()));
  }

  public static OneToOneAFooBinding toBinding(OneToOneAFoo domainObject) {
    OneToOneAFooBinding binding = new OneToOneAFooBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.oneToOneABars = domainObject.getOneToOneABar() == null ? null : new Link(domainObject.getOneToOneABar());
    return binding;
  }

  public static void toDomain(OneToOneAFooBinding binding, OneToOneAFoo domainObject) {
    domainObject.setName(binding.name);
    domainObject.setOneToOneABar(binding.oneToOneABars == null ? null : binding.oneToOneABars.getId() == null ? null : OneToOneABar.queries.find(binding.oneToOneABars.getId()));
  }

  public static OneToOneBBarBinding toBinding(OneToOneBBar domainObject) {
    OneToOneBBarBinding binding = new OneToOneBBarBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.oneToOneBFoo = domainObject.getOneToOneBFoo() == null ? null : new Link(domainObject.getOneToOneBFoo());
    return binding;
  }

  public static void toDomain(OneToOneBBarBinding binding, OneToOneBBar domainObject) {
    domainObject.setName(binding.name);
    domainObject.setOneToOneBFoo(binding.oneToOneBFoo == null ? null : binding.oneToOneBFoo.getId() == null ? null : OneToOneBFoo.queries.find(binding.oneToOneBFoo.getId()));
  }

  public static OneToOneBFooBinding toBinding(OneToOneBFoo domainObject) {
    OneToOneBFooBinding binding = new OneToOneBFooBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.oneToOneBBars = domainObject.getOneToOneBBars() == null ? null : new LinkCollection(0, domainObject.getOneToOneBBars());
    return binding;
  }

  public static void toDomain(OneToOneBFooBinding binding, OneToOneBFoo domainObject) {
    domainObject.setName(binding.name);
    final List<OneToOneBBar> oneToOneBBars = new ArrayList<OneToOneBBar>();
    for (final Link l : binding.oneToOneBBars.getLinks()) {
      OneToOneBBar o = l.getId() == null ? null : OneToOneBBar.queries.find(l.getId());
      oneToOneBBars.add(o);
    }
    domainObject.setOneToOneBBars(oneToOneBBars);
  }

  public static ParentBinding toBinding(Parent domainObject) {
    ParentBinding binding = new ParentBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.childs = domainObject.getChilds() == null ? null : new LinkCollection(0, domainObject.getChilds());
    return binding;
  }

  public static void toDomain(ParentBinding binding, Parent domainObject) {
    domainObject.setName(binding.name);
    final List<Child> childs = new ArrayList<Child>();
    for (final Link l : binding.childs.getLinks()) {
      Child o = l.getId() == null ? null : Child.queries.find(l.getId());
      childs.add(o);
    }
    domainObject.setChilds(childs);
  }

  public static ParentBChildBarBinding toBinding(ParentBChildBar domainObject) {
    ParentBChildBarBinding binding = new ParentBChildBarBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentBParent = domainObject.getParentBParent() == null ? null : new Link(domainObject.getParentBParent());
    return binding;
  }

  public static void toDomain(ParentBChildBarBinding binding, ParentBChildBar domainObject) {
    domainObject.setName(binding.name);
    domainObject.setParentBParent(binding.parentBParent == null ? null : binding.parentBParent.getId() == null ? null : ParentBParent.queries.find(binding.parentBParent.getId()));
  }

  public static ParentBChildFooBinding toBinding(ParentBChildFoo domainObject) {
    ParentBChildFooBinding binding = new ParentBChildFooBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentBParent = domainObject.getParentBParent() == null ? null : new Link(domainObject.getParentBParent());
    return binding;
  }

  public static void toDomain(ParentBChildFooBinding binding, ParentBChildFoo domainObject) {
    domainObject.setName(binding.name);
    domainObject.setParentBParent(binding.parentBParent == null ? null : binding.parentBParent.getId() == null ? null : ParentBParent.queries.find(binding.parentBParent.getId()));
  }

  public static ParentBParentBinding toBinding(ParentBParent domainObject) {
    ParentBParentBinding binding = new ParentBParentBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentBChildBars = domainObject.getParentBChildBars() == null ? null : new LinkCollection(0, domainObject.getParentBChildBars());
    binding.parentBChildFoos = domainObject.getParentBChildFoos() == null ? null : new LinkCollection(0, domainObject.getParentBChildFoos());
    return binding;
  }

  public static void toDomain(ParentBParentBinding binding, ParentBParent domainObject) {
    domainObject.setName(binding.name);
    final List<ParentBChildBar> parentBChildBars = new ArrayList<ParentBChildBar>();
    for (final Link l : binding.parentBChildBars.getLinks()) {
      ParentBChildBar o = l.getId() == null ? null : ParentBChildBar.queries.find(l.getId());
      parentBChildBars.add(o);
    }
    domainObject.setParentBChildBars(parentBChildBars);
    final List<ParentBChildFoo> parentBChildFoos = new ArrayList<ParentBChildFoo>();
    for (final Link l : binding.parentBChildFoos.getLinks()) {
      ParentBChildFoo o = l.getId() == null ? null : ParentBChildFoo.queries.find(l.getId());
      parentBChildFoos.add(o);
    }
    domainObject.setParentBChildFoos(parentBChildFoos);
  }

  public static ParentCBarBinding toBinding(ParentCBar domainObject) {
    ParentCBarBinding binding = new ParentCBarBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.firstParent = domainObject.getFirstParent() == null ? null : new Link(domainObject.getFirstParent());
    binding.secondParent = domainObject.getSecondParent() == null ? null : new Link(domainObject.getSecondParent());
    return binding;
  }

  public static void toDomain(ParentCBarBinding binding, ParentCBar domainObject) {
    domainObject.setName(binding.name);
    domainObject.setFirstParent(binding.firstParent == null ? null : binding.firstParent.getId() == null ? null : ParentCFoo.queries.find(binding.firstParent.getId()));
    domainObject.setSecondParent(binding.secondParent == null ? null : binding.secondParent.getId() == null ? null : ParentCFoo.queries.find(binding.secondParent.getId()));
  }

  public static ParentCFooBinding toBinding(ParentCFoo domainObject) {
    ParentCFooBinding binding = new ParentCFooBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.firstParentParentCBars = domainObject.getFirstParentParentCBars() == null ? null : new LinkCollection(0, domainObject.getFirstParentParentCBars());
    binding.secondParentParentCBars = domainObject.getSecondParentParentCBars() == null ? null : new LinkCollection(0, domainObject.getSecondParentParentCBars());
    return binding;
  }

  public static void toDomain(ParentCFooBinding binding, ParentCFoo domainObject) {
    domainObject.setName(binding.name);
    final List<ParentCBar> firstParentParentCBars = new ArrayList<ParentCBar>();
    for (final Link l : binding.firstParentParentCBars.getLinks()) {
      ParentCBar o = l.getId() == null ? null : ParentCBar.queries.find(l.getId());
      firstParentParentCBars.add(o);
    }
    domainObject.setFirstParentParentCBars(firstParentParentCBars);
    final List<ParentCBar> secondParentParentCBars = new ArrayList<ParentCBar>();
    for (final Link l : binding.secondParentParentCBars.getLinks()) {
      ParentCBar o = l.getId() == null ? null : ParentCBar.queries.find(l.getId());
      secondParentParentCBars.add(o);
    }
    domainObject.setSecondParentParentCBars(secondParentParentCBars);
  }

  public static ParentDBinding toBinding(ParentD domainObject) {
    ParentDBinding binding = new ParentDBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentDChildBs = domainObject.getParentDChildBs() == null ? null : new LinkCollection(0, domainObject.getParentDChildBs());
    return binding;
  }

  public static void toDomain(ParentDBinding binding, ParentD domainObject) {
    domainObject.setName(binding.name);
    final List<ParentDChildB> parentDChildBs = new ArrayList<ParentDChildB>();
    for (final Link l : binding.parentDChildBs.getLinks()) {
      ParentDChildB o = l.getId() == null ? null : ParentDChildB.queries.find(l.getId());
      parentDChildBs.add(o);
    }
    domainObject.setParentDChildBs(parentDChildBs);
  }

  public static ParentDChildABinding toBinding(ParentDChildA domainObject) {
    ParentDChildABinding binding = new ParentDChildABinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentD = domainObject.getParentD() == null ? null : new Link(domainObject.getParentD());
    return binding;
  }

  public static void toDomain(ParentDChildABinding binding, ParentDChildA domainObject) {
    domainObject.setName(binding.name);
    domainObject.setParentD(binding.parentD == null ? null : binding.parentD.getId() == null ? null : ParentD.queries.find(binding.parentD.getId()));
  }

  public static ParentDChildBBinding toBinding(ParentDChildB domainObject) {
    ParentDChildBBinding binding = new ParentDChildBBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentD = domainObject.getParentD() == null ? null : new Link(domainObject.getParentD());
    return binding;
  }

  public static void toDomain(ParentDChildBBinding binding, ParentDChildB domainObject) {
    domainObject.setName(binding.name);
    domainObject.setParentD(binding.parentD == null ? null : binding.parentD.getId() == null ? null : ParentD.queries.find(binding.parentD.getId()));
  }

  public static ParentDChildCBinding toBinding(ParentDChildC domainObject) {
    ParentDChildCBinding binding = new ParentDChildCBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentDs = domainObject.getParentDs() == null ? null : new LinkCollection(0, domainObject.getParentDs());
    return binding;
  }

  public static void toDomain(ParentDChildCBinding binding, ParentDChildC domainObject) {
    domainObject.setName(binding.name);
    final List<ParentD> parentDs = new ArrayList<ParentD>();
    for (final Link l : binding.parentDs.getLinks()) {
      ParentD o = l.getId() == null ? null : ParentD.queries.find(l.getId());
      parentDs.add(o);
    }
    domainObject.setParentDs(parentDs);
  }

  public static ParentDToChildCBinding toBinding(ParentDToChildC domainObject) {
    ParentDToChildCBinding binding = new ParentDToChildCBinding();
    binding.id = domainObject.getId();
    binding.version = domainObject.getVersion();
    binding.parentDChildC = domainObject.getParentDChildC() == null ? null : new Link(domainObject.getParentDChildC());
    binding.parentD = domainObject.getParentD() == null ? null : new Link(domainObject.getParentD());
    return binding;
  }

  public static void toDomain(ParentDToChildCBinding binding, ParentDToChildC domainObject) {
    domainObject.setParentDChildC(binding.parentDChildC == null ? null : binding.parentDChildC.getId() == null ? null : ParentDChildC.queries.find(binding.parentDChildC.getId()));
    domainObject.setParentD(binding.parentD == null ? null : binding.parentD.getId() == null ? null : ParentD.queries.find(binding.parentD.getId()));
  }

  public static ParentEBinding toBinding(ParentE domainObject) {
    ParentEBinding binding = new ParentEBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    binding.parentE = domainObject.getParentE() == null ? null : new Link(domainObject.getParentE());
    binding.parentEs = domainObject.getParentEs() == null ? null : new LinkCollection(0, domainObject.getParentEs());
    return binding;
  }

  public static void toDomain(ParentEBinding binding, ParentE domainObject) {
    domainObject.setName(binding.name);
    domainObject.setParentE(binding.parentE == null ? null : binding.parentE.getId() == null ? null : ParentE.queries.find(binding.parentE.getId()));
    final List<ParentE> parentEs = new ArrayList<ParentE>();
    for (final Link l : binding.parentEs.getLinks()) {
      ParentE o = l.getId() == null ? null : ParentE.queries.find(l.getId());
      parentEs.add(o);
    }
    domainObject.setParentEs(parentEs);
  }

  public static PrimitivesBinding toBinding(Primitives domainObject) {
    PrimitivesBinding binding = new PrimitivesBinding();
    binding.flag = domainObject.getFlag();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    return binding;
  }

  public static void toDomain(PrimitivesBinding binding, Primitives domainObject) {
    domainObject.setFlag(binding.flag);
    domainObject.setName(binding.name);
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

  public static void toDomain(PrimitivesBBinding binding, PrimitivesB domainObject) {
    domainObject.setBig1(binding.big1);
    domainObject.setBig2(binding.big2);
    domainObject.setBool1(binding.bool1);
    domainObject.setBool2(binding.bool2);
    domainObject.setBoolNullableWithDefaultFalse(binding.boolNullableWithDefaultFalse);
    domainObject.setBoolWithDefaultTrue(binding.boolWithDefaultTrue);
    domainObject.setInt1(binding.int1);
    domainObject.setInt2(binding.int2);
    domainObject.setSmall1(binding.small1);
    domainObject.setSmall2(binding.small2);
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

  public static void toDomain(PrimitivesCBinding binding, PrimitivesC domainObject) {
    domainObject.setDollarAmount(binding.dollarAmount);
    domainObject.setName(binding.name);
    domainObject.setTimestamp(binding.timestamp);
  }

  public static UserTypesAFooBinding toBinding(UserTypesAFoo domainObject) {
    UserTypesAFooBinding binding = new UserTypesAFooBinding();
    binding.created = domainObject.getCreated();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    return binding;
  }

  public static void toDomain(UserTypesAFooBinding binding, UserTypesAFoo domainObject) {
    domainObject.setCreated(binding.created);
    domainObject.setName(binding.name);
  }

  public static ValidationAFooBinding toBinding(ValidationAFoo domainObject) {
    ValidationAFooBinding binding = new ValidationAFooBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.version = domainObject.getVersion();
    return binding;
  }

  public static void toDomain(ValidationAFooBinding binding, ValidationAFoo domainObject) {
    domainObject.setName(binding.name);
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

  public static void toDomain(ValuesABinding binding, ValuesA domainObject) {
    domainObject.setA(binding.a);
    domainObject.setB(binding.b);
    domainObject.setI(binding.i);
    domainObject.setJ(binding.j);
    domainObject.setName(binding.name);
  }

  public static ValuesBBinding toBinding(ValuesB domainObject) {
    ValuesBBinding binding = new ValuesBBinding();
    binding.id = domainObject.getId();
    binding.name = domainObject.getName();
    binding.start = domainObject.getStart();
    binding.version = domainObject.getVersion();
    return binding;
  }

  public static void toDomain(ValuesBBinding binding, ValuesB domainObject) {
    domainObject.setName(binding.name);
    domainObject.setStart(binding.start);
  }

}
