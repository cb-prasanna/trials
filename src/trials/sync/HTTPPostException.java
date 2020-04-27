package trials.sync;

import java.net.URI;

public class HTTPPostException extends HTTPBaseException {
  public HTTPPostException(URI uri, String body, int status_code) {
    super(uri, body, status_code);
  }
}
