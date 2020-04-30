package trials.sync;

import com.chargebee.org.json.JSONException;
import com.chargebee.org.json.JSONObject;
import trials.config.IntegrationConfig;
import trials.integ.chargebee.ChargebeeAPIIterator;
import trials.integ.chargebee.ChargebeeThirdPartyEntityMappingUtil;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public interface SyncSource {

    //wil return customer data

    public static Iterator<SyncSourceEntity> prepareData(IntegrationConfig.Integration integration,
                                   SourceEntityTypes sourceType) throws Exception {
        ChargebeeAPIIterator customer = new ChargebeeAPIIterator("customer");
        ChargebeeThirdPartyEntityMappingUtil tpem =
                new ChargebeeThirdPartyEntityMappingUtil(SourceEntityTypes.Customer,
                        integration.toString());
        while (customer.hasNext()) {
            SyncSourceEntity next = customer.next();
            tpem.updateMapping(next.getString("id"), new JSONObject(next.getResource()));
        }
        tpem.getDataForSync(10);
        return tpem;

    }

}
