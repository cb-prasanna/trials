package trails;

import org.json.JSONObject;

public interface SyncDestinationEntity {
    public int getInt(String name);
    public String getString(String name);
    public double getDouble(String name);
    public boolean getBoolean(String name);
    public void setInt(String name, int value);
    public void setString(String name, String value);
    public void setDouble(String name, Double value);
    public void setBoolean(String name, boolean value);
}