package trials;

import java.net.URI;
import java.util.Map;

public interface RestClient {
  String post(URI uri, Map<String, Object> post_params);
}
