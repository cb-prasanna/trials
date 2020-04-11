package trials;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author cb-prasanna
 */
public class ChargebeeCustomerSyncSource implements SyncSource {

    DataFetchLayer dataLayer;

    public ChargebeeSyncSource(DataFetchLayer db) {
        this.dataLayer = db;
    }

    public hasNext(){
        // check for if there is any data left

    }
    @Override
    public SyncEntities next() {
        dataLayer.getData("Customer");
        dataLayer.getData("Contacts");
        dataLayer.getData("Subscriptions");
        return dataLayer.getData("Subscriptions");
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
    public Map<String, Boolean> updateBatch(List<SyncSource> syncSources) {
        return null;
    }
}
