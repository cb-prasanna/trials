package trials.sync;

import com.chargebee.org.json.JSONException;
import com.chargebee.org.json.JSONObject;
import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SyncDestinationEntityBase implements SyncDestinationEntity {
  private static final Logger log = LoggerFactory.getLogger(SyncDestinationEntityBase.class);
  private JSONObject data;
  private DestinationEntityTypes type;
  private Set<String> properties;
  private boolean isNew;

  public SyncDestinationEntityBase(DestinationEntityTypes type) {
    data = new JSONObject();
    properties = new HashSet<>();
    this.type = type;
    this.isNew = false;
  }

  @Override
  public DestinationEntityTypes getType() {
    return type;
  }

  @Override
  public int getInt(String name) throws JSONException {
    return data.getInt(name);
  }

  @Override
  public String getString(String name) throws JSONException {
    return data.getString(name);
  }

  @Override
  public double getDouble(String name) throws JSONException {
    return data.getDouble(name);
  }

  @Override
  public boolean getBoolean(String name) throws JSONException {
    return data.getBoolean(name);
  }

  @Override
  public void setInt(String name, int value) throws JSONException {
    properties.add(name);
    data.put(name, value);
  }

  @Override
  public void setString(String name, String value) throws JSONException {
    log.info("Writing Name " + name);
    properties.add(name);
    data.put(name, value);
  }

  @Override
  public void setDouble(String name, Double value) throws JSONException {
    properties.add(name);
    data.put(name, value);
  }

  @Override
  public void setBoolean(String name, boolean value) throws JSONException {
    properties.add(name);
    data.put(name, value);
  }

  @Override
  public void setNew() {
    this.isNew = true;
  }

  @Override
  public boolean isNew() {
    return this.isNew;
  }
}
