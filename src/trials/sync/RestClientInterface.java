package trials.sync;

import java.net.URI;
import java.util.Map;

public interface RestClientInterface {
  String post(URI uri, Map<String, String> headers, Map<String, String> postParams) throws HTTPPostException;
  String post(URI uri, Map<String, String> headers, String postBody) throws HTTPPostException;
  String put(URI uri, Map<String, String> headers, Map<String, String> putParams) throws HTTPPutException;
  String get(URI uri, Map<String, String> headers, Map<String, String> getParams) throws HTTPGetException;
}
