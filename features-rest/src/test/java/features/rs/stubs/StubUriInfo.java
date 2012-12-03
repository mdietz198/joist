package features.rs.stubs;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.sun.jersey.core.util.MultivaluedMapImpl;

public class StubUriInfo implements UriInfo {

  private MultivaluedMap<String, String> queryParameters = new MultivaluedMapImpl();

  @Override
  public String getPath() {
    return null;
  }

  @Override
  public String getPath(boolean decode) {
    return null;
  }

  @Override
  public List<PathSegment> getPathSegments() {
    return null;
  }

  @Override
  public List<PathSegment> getPathSegments(boolean decode) {
    return null;
  }

  @Override
  public URI getRequestUri() {
    return null;
  }

  @Override
  public UriBuilder getRequestUriBuilder() {
    return null;
  }

  @Override
  public URI getAbsolutePath() {
    return null;
  }

  @Override
  public UriBuilder getAbsolutePathBuilder() {
    return null;
  }

  @Override
  public URI getBaseUri() {
    return null;
  }

  @Override
  public UriBuilder getBaseUriBuilder() {
    return null;
  }

  @Override
  public MultivaluedMap<String, String> getPathParameters() {
    return null;
  }

  @Override
  public MultivaluedMap<String, String> getPathParameters(boolean decode) {
    return null;
  }

  @Override
  public MultivaluedMap<String, String> getQueryParameters() {
    return this.queryParameters;
  }

  public StubUriInfo setQueryParameters(MultivaluedMap<String, String> parameters) {
    this.queryParameters = parameters;
    return this;
  }

  @Override
  public MultivaluedMap<String, String> getQueryParameters(boolean decode) {
    return this.queryParameters;
  }

  @Override
  public List<String> getMatchedURIs() {
    return null;
  }

  @Override
  public List<String> getMatchedURIs(boolean decode) {
    return null;
  }

  @Override
  public List<Object> getMatchedResources() {
    return null;
  }

}
