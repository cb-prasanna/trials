package trials.sync;

import com.fasterxml.jackson.jr.ob.JSON;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.AbstractMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OAuth2 implements OAuthInterface {

  private static final Logger log = LoggerFactory.getLogger(OAuth2.class);
  private URI redirectUrl;
  private String clientId;
  private String clientSecret;
  private String refreshToken;
  private String accessToken;
  private URI refreshTokenUrl;
  private LocalDateTime expiryTime;
  private String identifier; // Unique identifier for logging purpose

  private Map<String, String> getAuthHeadersInternal() throws AccessTokenExpired {
    if (accessTokenExpired()) {
      throw new AccessTokenExpired("Access Token Expired, refresh token url " + refreshTokenUrl);
    }
    return Stream.of(
      kv("Authorization", "Bearer " + accessToken),
      kv("Content-Type", "application/json")
    ).collect(entriesToMap());
  }

  public String dump() throws IOException {
    Map<String, String> outMap = Stream.of(
        kv("client_id", clientId),
        kv("client_secret", clientSecret),
        kv("refresh_token", refreshToken),
        kv("redirect_url", redirectUrl.toString()),
        kv("access_token", accessToken),
        kv("refresh_token_url", refreshTokenUrl.toString()),
        kv("expiry_time", expiryTime.toString()),
        kv("identifier", identifier)
    ).collect(entriesToMap());

    return JSON.std.asString(outMap);
  }

  @Override
  public Map<String, String> getAuthHeaders(RestClientInterface client)
      throws AccessTokenExpired, HTTPPostException, IOException {
    if (accessTokenExpired()) {
      refreshToken(client);
    }
    return getAuthHeadersInternal();
  }

  @Override
  public void refreshToken(RestClientInterface client) throws IOException, HTTPPostException {
    String response = client.post(refreshTokenUrl, null, getRefreshParams());
    resetData(response);
  }

  private Map<String, String> getRefreshParams() {
    log.info("Refreshing Token for " + identifier);
    return Stream.of(
        kv("client_id", clientId),
        kv("client_secret", clientSecret),
        kv("refresh_token", refreshToken),
        kv("grant_type", "refresh_token"),
        kv("redirect_url", redirectUrl.toString())
    ).collect(entriesToMap());
  }

  // From: https://minborgsjavapot.blogspot.com/2014/12/java-8-initializing-maps-in-smartest-way.html
  private static Map.Entry<String, String> kv(String key, String value) {
    return new AbstractMap.SimpleEntry<>(key, value);
  }

  private static <K, U> Collector<Entry<K, U>, ?, Map<K, U>> entriesToMap() {
    return Collectors.toMap((e) -> e.getKey(), (e) -> e.getValue());
  }

  public OAuth2(String inputJson) throws IOException, URISyntaxException {
    Map<String, Object> response = JSON.std.mapFrom(inputJson);
    clientId = getString(response, "client_id");
    clientSecret = getString(response, "client_secret");
    refreshToken = getString(response, "refresh_token");
    accessToken = getString(response,"access_token");
    redirectUrl = new URI(getString(response, "redirect_url"));
    refreshTokenUrl = new URI(getString(response, "refresh_token_url"));
    expiryTime = LocalDateTime.parse(getString(response, "expiry_time"));
    identifier = getString(response, "identifier");
  }

  public OAuth2(
      String clientId,
      String clientSecret,
      String refreshToken,
      String accessToken,
      String redirectUrl,
      String refreshTokenUrl,
      String expiryTime,
      String identifier
  ) throws URISyntaxException {

    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.refreshToken = refreshToken;
    this.accessToken = accessToken;
    this.redirectUrl = new URI(redirectUrl);
    this.refreshTokenUrl = new URI(refreshTokenUrl);
    this.expiryTime = LocalDateTime.parse(expiryTime);
    this.identifier = identifier;
  }

  private String getString(Map<String, Object> map, String key) throws IOException {
    Object response = map.get(key);
    if (response == null) {
      throw new IOException("Unable to get key " + key + " OAUTH data " + map.toString());
    }
    return response.toString();
  }

  private int getInt(Map<String, Object> map, String key) throws IOException {
    Object response = map.get(key);
    if (response == null) {
      throw new IOException("Unable to get key " + key + " OAUTH data " + map.toString());
    }
    return Integer.valueOf(response.toString());
  }

  private void resetData(String jsonString) throws IOException {
    Map<String, Object> response = JSON.std.mapFrom(jsonString);
    refreshToken = getString(response, "refresh_token");
    accessToken = getString(response, "access_token");
    int expiresIn = getInt(response, "expires_in");
    expiryTime = LocalDateTime.now().plus(expiresIn, ChronoUnit.SECONDS);
    log.info("Next Expiry Time " + expiryTime.toString());
  }

  private boolean accessTokenExpired() {
    if (expiryTime.isBefore(LocalDateTime.now()))
      return true;
    return false;
  }
}
