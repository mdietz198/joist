package features.rs.resources;

import com.sun.jersey.api.uri.UriBuilderImpl;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import joist.rs.CollectionLinkBinding;

@Path("/")
public class ServiceRootResource {

  @GET
  @Produces({ "application/xml", "application/json" })
  public List<CollectionLinkBinding> get() {
    List<CollectionLinkBinding> links = new ArrayList<CollectionLinkBinding>();
    CollectionLinkBinding link;
    link = new CollectionLinkBinding("childs", new UriBuilderImpl().path(ChildResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("childFs", new UriBuilderImpl().path(ChildFResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("childGs", new UriBuilderImpl().path(ChildGResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("codeADomainObjects", new UriBuilderImpl().path(CodeADomainObjectResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("grandChilds", new UriBuilderImpl().path(GrandChildResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("historyEntrys", new UriBuilderImpl().path(HistoryEntryResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("inheritanceAOwners", new UriBuilderImpl().path(InheritanceAOwnerResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("inheritanceASubOnes", new UriBuilderImpl().path(InheritanceASubOneResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("inheritanceASubTwos", new UriBuilderImpl().path(InheritanceASubTwoResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("inheritanceAThings", new UriBuilderImpl().path(InheritanceAThingResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("inheritanceBBottoms", new UriBuilderImpl().path(InheritanceBBottomResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("inheritanceBRootChilds", new UriBuilderImpl().path(InheritanceBRootChildResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("inheritanceCFoo1s", new UriBuilderImpl().path(InheritanceCFoo1ResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("inheritanceCFoo2s", new UriBuilderImpl().path(InheritanceCFoo2ResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("manyToManyABars", new UriBuilderImpl().path(ManyToManyABarResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("manyToManyAFoos", new UriBuilderImpl().path(ManyToManyAFooResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("manyToManyAFooToBars", new UriBuilderImpl().path(ManyToManyAFooToBarResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("manyToManyBBars", new UriBuilderImpl().path(ManyToManyBBarResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("manyToManyBFoos", new UriBuilderImpl().path(ManyToManyBFooResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("manyToManyBFooToBars", new UriBuilderImpl().path(ManyToManyBFooToBarResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("oneToOneABars", new UriBuilderImpl().path(OneToOneABarResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("oneToOneAFoos", new UriBuilderImpl().path(OneToOneAFooResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("oneToOneBBars", new UriBuilderImpl().path(OneToOneBBarResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("oneToOneBFoos", new UriBuilderImpl().path(OneToOneBFooResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("parents", new UriBuilderImpl().path(ParentResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("parentBChildBars", new UriBuilderImpl().path(ParentBChildBarResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("parentBChildFoos", new UriBuilderImpl().path(ParentBChildFooResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("parentBParents", new UriBuilderImpl().path(ParentBParentResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("parentCBars", new UriBuilderImpl().path(ParentCBarResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("parentCFoos", new UriBuilderImpl().path(ParentCFooResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("parentDs", new UriBuilderImpl().path(ParentDResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("parentDChildAs", new UriBuilderImpl().path(ParentDChildAResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("parentDChildBs", new UriBuilderImpl().path(ParentDChildBResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("parentDChildCs", new UriBuilderImpl().path(ParentDChildCResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("parentDToChildCs", new UriBuilderImpl().path(ParentDToChildCResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("parentEs", new UriBuilderImpl().path(ParentEResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("parentFs", new UriBuilderImpl().path(ParentFResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("parentGs", new UriBuilderImpl().path(ParentGResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("primitivess", new UriBuilderImpl().path(PrimitivesResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("primitivesBs", new UriBuilderImpl().path(PrimitivesBResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("primitivesCs", new UriBuilderImpl().path(PrimitivesCResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("userTypesAFoos", new UriBuilderImpl().path(UserTypesAFooResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("validationAFoos", new UriBuilderImpl().path(ValidationAFooResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("valuesAs", new UriBuilderImpl().path(ValuesAResourceCollectionCodegen.class).build().toString());
    links.add(link);
    link = new CollectionLinkBinding("valuesBs", new UriBuilderImpl().path(ValuesBResourceCollectionCodegen.class).build().toString());
    links.add(link);
    return links;
  }

}
