package joist.rs;

import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang.StringUtils;

@XmlTransient
public class AbstractLinkBinding {

  // TODO find better way to inject base service URL than a system property
  private static final String baseUrl = StringUtils.removeEnd(System.getProperty("baseServiceUrl"), "/");;

  private String name;
  private String relativeUrl;

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRelativeUrl() {
    return this.relativeUrl;
  }

  public void setRelativeUrl(String relativeUrl) {
    this.relativeUrl = relativeUrl;
  }

  public String getUrl() {
    if (AbstractLinkBinding.baseUrl == null) {
      return null;
    }
    return AbstractLinkBinding.baseUrl + this.relativeUrl;
  }

  public void setUrl(String url) {
    if (AbstractLinkBinding.baseUrl != null) {
      this.setRelativeUrl(url.replace(AbstractLinkBinding.baseUrl, ""));
    }
  }
}
