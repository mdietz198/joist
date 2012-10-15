package joist.rs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import joist.domain.orm.Repository;

public abstract class AbstractResource<T> {

  public abstract T get(final @Context Repository repo, final @PathParam("id") Long id);

  @GET
  @Path("json")
  @Produces({ "application/json" })
  public T getJson(final @Context Repository repo, final @PathParam("id") Long id) {
    return this.get(repo, id);
  }

}
