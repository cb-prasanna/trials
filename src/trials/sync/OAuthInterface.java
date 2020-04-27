package trials.sync;

import java.io.IOException;
import java.util.Map;

public interface OAuthInterface {

  Map<String, String> getAuthHeaders(RestClientInterface client)
      throws AccessTokenExpired, HTTPPostException, IOException;
  void refreshToken(RestClientInterface client) throws IOException, HTTPPostException;
}
