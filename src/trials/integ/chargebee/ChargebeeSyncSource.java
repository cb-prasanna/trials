package trials.integ.chargebee;

import com.chargebee.org.json.JSONObject;
import trials.config.IntegrationConfig;
import trials.sync.SourceEntityTypes;
import trials.sync.SyncSource;
import trials.sync.SyncSourceEntity;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author cb-prasanna
 */
public class ChargebeeSyncSource implements SyncSource {

    Iterator<SyncSourceEntity> iterator;

    public ChargebeeSyncSource(Iterator<SyncSourceEntity> iterator) {
        this.iterator = iterator;
    }

    public static Iterator<SyncSourceEntity> getBatch(IntegrationConfig.Integration integration,
                                                      SourceEntityTypes sourceType) throws Exception {
        ChargebeeAPIIterator customer = new ChargebeeAPIIterator("customer");
        ChargebeeThirdPartyEntityMappingUtil tpem =
                new ChargebeeThirdPartyEntityMappingUtil(SourceEntityTypes.Customer,
                        integration.toString());
        while (customer.hasNext()) {
            SyncSourceEntity next = customer.next();
            tpem.updateMapping(next.getString("id"), new JSONObject((next.getResource())));
        }
        tpem.getDataForSync(100);
        return tpem;

    }

    public SyncSourceEntity getNext() {
        return iterator.next();
    }

    public Map<String, Boolean> updateBatch(List<SyncSource> syncSources) {
        return null;
    }
}
