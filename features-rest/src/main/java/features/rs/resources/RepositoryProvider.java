package features.rs.resources;

import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import joist.domain.orm.Repository;

import com.sun.jersey.spi.inject.SingletonTypeInjectableProvider;

import features.Registry;

@Provider
public class RepositoryProvider extends SingletonTypeInjectableProvider<Context, Repository> {

  public RepositoryProvider() {
    super(Repository.class, Registry.getRepository());
  }
}
