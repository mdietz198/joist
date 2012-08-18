package joist.rs.codegen.entities;

import java.util.ArrayList;
import java.util.List;

import joist.codegen.dtos.Entity;
import joist.codegen.dtos.ManyToManyProperty;
import joist.codegen.dtos.ManyToOneProperty;
import joist.codegen.dtos.OneToManyProperty;
import joist.codegen.dtos.PrimitiveProperty;
import joist.rs.codegen.Config;

// Wraps an entity to let me apply rest specific logic to the Entity
public class RestEntity {

  public Entity entity;

  public RestEntity(Entity entity) {
    this.entity = entity;
  }

  public Config getRsConfig() {
    return (Config) this.entity.getConfig();
  }

  public String getFullResourceClassName() {
    return this.getRsConfig().getResourcePackage() + "." + this.getResourceClassName();
  }

  public String getFullResourceCollectionClassName() {
    return this.getRsConfig().getResourcePackage() + "." + this.getResourceCollectionClassName();
  }

  public String getResourceClassName() {
    return this.entity.getClassName() + "ResourceCodegen";
  }

  public String getResourceCollectionClassName() {
    return this.entity.getClassName() + "ResourceCollectionCodegen";
  }

  public String getFullBindingClassName() {
    return this.getRsConfig().getBindingPackage() + "." + this.getBindingClassName();
  }

  public String getBindingClassName() {
    return this.entity.getClassName() + "Binding";
  }

  public String getParentBindingClassName() {
    if (this.entity.isSubclass()) {
      return new RestEntity(this.entity.getBaseEntity()).getBindingClassName();
    } else {
      return "Object";
    }
  }

  public List<PrimitiveProperty> getPrimitivePropertiesIncludingInherited() {
    List<PrimitiveProperty> props = new ArrayList<PrimitiveProperty>();
    if (this.entity.getBaseEntity() != null) {
      props.addAll(new RestEntity(this.entity.getBaseEntity()).getPrimitivePropertiesIncludingInherited());
    }
    props.addAll(this.entity.getPrimitiveProperties());
    return props;
  }

  public List<ManyToOneProperty> getManyToOnePropertiesIncludingInherited() {
    List<ManyToOneProperty> props = new ArrayList<ManyToOneProperty>();
    if (this.entity.getBaseEntity() != null) {
      props.addAll(new RestEntity(this.entity.getBaseEntity()).getManyToOnePropertiesIncludingInherited());
    }
    props.addAll(this.entity.getManyToOneProperties());
    return props;
  }

  public List<OneToManyProperty> getOneToManyPropertiesIncludingInherited() {
    List<OneToManyProperty> props = new ArrayList<OneToManyProperty>();
    if (this.entity.getBaseEntity() != null) {
      props.addAll(new RestEntity(this.entity.getBaseEntity()).getOneToManyPropertiesIncludingInherited());
    }
    props.addAll(this.entity.getOneToManyProperties());
    return props;
  }

  public List<ManyToManyProperty> getManyToManyPropertiesIncludingInherited() {
    List<ManyToManyProperty> props = new ArrayList<ManyToManyProperty>();
    if (this.entity.getBaseEntity() != null) {
      props.addAll(new RestEntity(this.entity.getBaseEntity()).getManyToManyPropertiesIncludingInherited());
    }
    props.addAll(this.entity.getManyToManyProperties());
    return props;
  }

}
