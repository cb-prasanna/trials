package trials.model;

import java.net.URI;
import java.util.Map;
import trials.RestClient;
import kong.unirest.Unirest;

public class OAuthClient implements RestClient {

  public String post(URI uri, Map<String, Object> postParams) {
    return Unirest.post(uri.toString()).fields(postParams).asString();
  }
}
