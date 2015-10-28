package org.exoplatform.addons.es.client;

import org.apache.commons.lang.StringUtils;

import org.exoplatform.commons.utils.PropertyManager;
import org.exoplatform.services.log.ExoLogger;
import org.exoplatform.services.log.Log;

/**
 * Created by The eXo Platform SAS Author : eXoPlatform exo@exoplatform.com
 * 9/14/15
 */
public class ElasticSearchingClient extends ElasticClient {
  private static final Log LOG = ExoLogger.getLogger(ElasticSearchingClient.class);

  private static final String ES_SEARCH_CLIENT_PROPERTY_NAME = "exo.es.search.server.url";
  private static final String ES_SEARCH_CLIENT_PROPERTY_USERNAME = "exo.es.search.server.username";
  private static final String ES_SEARCH_CLIENT_PROPERTY_PASSWORD = "exo.es.search.server.password";


  public ElasticSearchingClient() {
    super();
    //Get url client from exo global properties
    if (StringUtils.isNotBlank(PropertyManager.getProperty(ES_SEARCH_CLIENT_PROPERTY_NAME))) {
      this.urlClient = PropertyManager.getProperty(ES_SEARCH_CLIENT_PROPERTY_NAME);
      LOG.info("Using {} as Searching URL", this.urlClient);
    } else {
      LOG.info("Using default as Searching URL");
    }
  }

  public String sendRequest(String esQuery, String index, String type) {
    ElasticResponse response = sendHttpPostRequest(urlClient + "/" + index + "/" + type + "/_search", esQuery);
    return response.getMessage();
  }

  @Override
  protected String getEsUsernameProperty() {
    return PropertyManager.getProperty(ES_SEARCH_CLIENT_PROPERTY_USERNAME);
  }

  @Override
  protected String getEsPasswordProperty() {
    return PropertyManager.getProperty(ES_SEARCH_CLIENT_PROPERTY_PASSWORD);
  }

}
