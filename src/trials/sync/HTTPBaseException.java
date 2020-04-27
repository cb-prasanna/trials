package trials.sync;

import java.net.URI;

public class HTTPBaseException extends Throwable {
  public URI uri;
  public String body;
  public int status_code;

  public HTTPBaseException(URI uri, String body, int status_code) {
    this.uri = uri;
    this.body = body;
    this.status_code = status_code;
  }

  public String getMessage() {
    return "Return Code: " + String.valueOf(status_code) + ":" + body + " URI: " + uri;
  }

}
