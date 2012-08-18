package joist.rs.codegen.entities;

import joist.codegen.dtos.Entity;
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

  public String getResourceClassName() {
    return this.entity.getClassName() + "ResourceCodegen";
  }

}
