package trials.sync;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import kong.unirest.Unirest;
import trials.config.SystemConfig;

public class RestClient implements RestClientInterface{

  public RestClient() {

    Unirest.config()
        .socketTimeout(SystemConfig.net.socket_timeout)
        .connectTimeout(SystemConfig.net.connect_timeout);
  }

  @Override
  public String post(URI uri, Map<String, String> headers, Map<String, String> postParams)
      throws HTTPPostException {

    if (headers == null) headers = new HashMap<String, String>();
    if (postParams == null) postParams = new HashMap<String, String>();
    var response = Unirest.post(uri.toString())
        .headers(headers)
        .fields(Collections.<String, Object>unmodifiableMap(postParams)).asString();

    if (response.isSuccess()) {
      return response.getBody();
    } else {
      throw new HTTPPostException(uri, response.getBody(), response.getStatus());
    }
  }

  @Override
  public String post(URI uri, Map<String, String> headers, String postBody)
      throws HTTPPostException {
    if (headers == null) headers = new HashMap<String, String>();
    var response = Unirest.post(uri.toString())
        .headers(headers)
        .body(postBody).asString();

    if (response.isSuccess()) {
      return response.getBody();
    } else {
      throw new HTTPPostException(uri, response.getBody(), response.getStatus());
    }
  }

  @Override
  public String put(URI uri, Map<String, String> headers, Map<String, String> putParams)
      throws HTTPPutException {
    if (headers == null) headers = new HashMap<String, String>();
    if (putParams == null) putParams = new HashMap<String, String>();
    var response = Unirest.put(uri.toString())
        .headers(headers)
        .fields(Collections.<String, Object>unmodifiableMap(putParams)).asString();

    if (response.isSuccess()) {
      return response.getBody();
    } else {
      throw new HTTPPutException(uri, response.getBody(), response.getStatus());
    }
  }

  @Override
  public String get(URI uri, Map<String, String> headers, Map<String, String> getParams)
      throws HTTPGetException {
    if (headers == null) headers = new HashMap<String, String>();
    if (getParams == null) getParams = new HashMap<String, String>();
    var response = Unirest.get(uri.toString())
        .headers(headers)
        .queryString(Collections.<String, Object>unmodifiableMap(getParams)).asString();

    if (response.isSuccess()) {
      return response.getBody();
    } else {
      throw new HTTPGetException(uri, response.getBody(), response.getStatus());
    }
  }
}
