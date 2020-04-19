package trials.integ.hubspot;

import trials.sync.Field;
import trials.sync.SyncDestination;
import trials.sync.SyncSourceEntity;

import java.util.List;
import java.util.Map;

public class HubspotSyncDestination implements SyncDestination {
    @Override
    public Map<SyncSourceEntity, Boolean> findBatch(Field field, List<?> ids) {
        return null;
    }

    @Override
    public void createCustomFields() {

    }

    @Override
    public Integer getInt(String key) {
        return null;
    }

    @Override
    public String getString(String key) {
        return null;
    }

    @Override
    public Double getDouble(String key) {
        return null;
    }

    @Override
    public void setInt(String key, Integer value) {

    }

    @Override
    public void setString(String key, String value) {

    }

    @Override
    public void setDouble(String key, Double value) {

    }
}
