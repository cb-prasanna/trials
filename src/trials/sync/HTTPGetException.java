package trials.sync;

import java.net.URI;

public class HTTPGetException extends HTTPBaseException {
  public HTTPGetException(URI uri, String body, int status_code) {
    super(uri, body, status_code);
  }
};
