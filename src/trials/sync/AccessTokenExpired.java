package trials.sync;

public class AccessTokenExpired extends Throwable {

  private final String error_message;

  public AccessTokenExpired(String s) {
    this.error_message = s;
  }
}
