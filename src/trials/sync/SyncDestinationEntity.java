package trials.sync;

import java.util.List;
import java.util.Map;

public interface SyncDestinationEntity {
    Map<SyncDestinationEntity, Boolean> findBatch(Field field, List<?> ids);

    int getInt(String name);

    String getString(String name);

    double getDouble(String name);

    boolean getBoolean(String name);

    void setInt(String name, int value);

    void setString(String name, String value);

    void setDouble(String name, Double value);

    void setBoolean(String name, boolean value);
}