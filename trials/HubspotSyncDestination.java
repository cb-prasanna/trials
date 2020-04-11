package trials;

import java.util.List;

public class HubspotSyncDestination implements SyncDestination<SyncEntities> {

    @Override
    public List<SyncEntities> findBatch(Field field, List<?> ids) {
        return null;
    }

    @Override
    public void updateBatch(SyncEntities syncEntities) {
        System.out.println("Updating hubspot with Received Entities");
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
