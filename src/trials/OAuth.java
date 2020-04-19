package trials;

import java.net.URI;
import org.apache.commons.lang3.tuple.Pair;

public interface OAuth {

  Pair<String, String> getClientIDSecret();

  String getAccessToken();
  String getRefreshToken();
  String hasTokenExpired();
  public boolean refreshToken(RestClient client, URI url);

}
