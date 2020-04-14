package trials.syncDestination;

import trials.model.enums.Field;

import java.util.List;
import java.util.Map;

public interface SyncDestinationEntity {
    Map<SyncDestinationEntity, Boolean> findBatch(Field field, List<?> ids );

    public int getInt(String name);
    public String getString(String name);
    public double getDouble(String name);
    public boolean getBoolean(String name);
    public void setInt(String name, int value);
    public void setString(String name, String value);
    public void setDouble(String name, Double value);
    public void setBoolean(String name, boolean value);
}