package joist.codegen.dtos;

import java.util.List;

import joist.codegen.CodegenConfig;
import joist.codegen.InformationSchemaColumn;
import joist.util.Inflector;

import org.apache.commons.lang.StringUtils;

/** For entities that are the target of a many-to-one. */
public class OneToManyProperty {

  private CodegenConfig config;
  private Entity oneSide;
  private String constraintName;
  private String keyColumnName;
  private String capitalVariableNameSingular;
  private ManyToOneProperty manyToOneProperty;
  private boolean oneToOne = false;

  /** Parent (oneSide) -> child (manySide) */
  public OneToManyProperty(Entity oneSide, InformationSchemaColumn manySide) {
    this.config = oneSide.config;
    this.oneSide = oneSide;
    this.constraintName = manySide.foreignKeyConstraintName;
    this.keyColumnName = manySide.name;
  }

  public Entity getManySide() {
    return this.getManyToOneProperty().getManySide();
  }

  public Entity getOneSide() {
    return this.oneSide;
  }

  public String getCapitalVariableNameSingular() {
    if (this.capitalVariableNameSingular == null) {
      if (this.getKeyPropertyName().equals(this.oneSide.getClassName())) {
        // Regular many-to-one relationship of only 1 column in the target table pointing to us, so name
        // our side based on the type e.g. child.parent_id, so return "Child" to get "parent.getChilds()"
        this.capitalVariableNameSingular = this.getTargetJavaType();
      } else {
        // Boundary case of the target table's column is not "parent_id", e.g. it's
        // "child.first_parent_id" or even just "child.first_id". So, on the parent
        // size, first drop "parent_id" before suffix Child, to get "parent.getFirstChilds()"
        this.capitalVariableNameSingular = this.getKeyPropertyName().replace(this.getOneSide().getClassName(), "") + this.getTargetJavaType();
      }
    }
    return this.capitalVariableNameSingular;
  }

  public String getCapitalVariableName() {
    return this.getCapitalVariableNameSingular() + "s";
  }

  public String getJavaType() {
    return "List<" + this.getTargetJavaType() + ">";
  }

  public String getDefaultJavaString() {
    return "new ArrayList<" + this.getTargetJavaType() + ">()";
  }

  public List<String> getCustomRules() {
    return this.config.getCustomRules(this.getManySide().getClassName(), this.getJavaType(), this.getVariableName());
  }

  public String getKeyPropertyName() {
    return Inflector.camelize(this.keyColumnName.replaceAll("_id$", ""));
  }

  public String getKeyFieldName() {
    String theirPropertyName = Inflector.camelize(this.keyColumnName.replaceAll("_id$", ""));
    return StringUtils.uncapitalize(theirPropertyName);
  }

  public String getVariableName() {
    return StringUtils.uncapitalize(this.getCapitalVariableName());
  }

  public String getVariableNameSingular() {
    return StringUtils.uncapitalize(this.getCapitalVariableNameSingular());
  }

  public String getTargetJavaType() {
    return this.getManySide().getClassName();
  }

  public boolean isOwnerMe() {
    return this.constraintName.contains("_isthem"); // Since we're incoming, "them" means us
  }

  public boolean isOwnerThem() {
    return this.constraintName.contains("_isme"); // Since we're incoming, "me" means them
  }

  public boolean getNoTicking() {
    return this.config.isDoNotIncrementParentsOpLock(this.oneSide.getClassName(), this.getVariableName());
  }

  public boolean isCollectionSkipped() {
    return this.config.isCollectionSkipped(this.oneSide.getClassName(), this.getVariableName());
  }

  public ManyToOneProperty getManyToOneProperty() {
    return this.manyToOneProperty;
  }

  public void setManyToOneProperty(ManyToOneProperty foreignKeyColumn) {
    this.manyToOneProperty = foreignKeyColumn;
  }

  public boolean isOneToOne() {
    return this.oneToOne;
  }

  public void setOneToOne(boolean oneToOne) {
    this.oneToOne = oneToOne;
  }

}
