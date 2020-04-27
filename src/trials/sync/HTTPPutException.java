package trials.sync;

import java.net.URI;

public class HTTPPutException extends HTTPBaseException {
  public HTTPPutException(URI uri, String body, int status_code) {
    super(uri, body, status_code);
  }
}
