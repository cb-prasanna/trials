package trials.sync;

import com.chargebee.org.json.JSONException;

public interface SyncDestinationEntity {

    DestinationEntityTypes getType();

    int getInt(String name) throws JSONException;

    String getString(String name) throws JSONException;

    double getDouble(String name) throws JSONException;

    boolean getBoolean(String name) throws JSONException;

    void setInt(String name, int value) throws JSONException;

    void setString(String name, String value) throws JSONException;

    void setDouble(String name, Double value) throws JSONException;

    void setBoolean(String name, boolean value) throws JSONException;
}