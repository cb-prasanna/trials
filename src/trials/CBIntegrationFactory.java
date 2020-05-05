package trials;

import java.io.IOException;
import java.net.URISyntaxException;

import com.chargebee.models.ThirdPartyEntityMapping;
import com.chargebee.org.json.JSONException;
import com.stripe.model.Charge;
import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import org.apache.axis.types.Entity;
import trials.config.SystemConfig;
import trials.integ.chargebee.ChargebeeAPIIterator;
import trials.integ.chargebee.ChargebeeSyncSource;
import trials.integ.chargebee.ChargebeeThirdPartyEntityMappingUtil;
import trials.integ.hubspot.HubspotSyncDestination;
import com.chargebee.org.json.JSONObject;
import trials.matcher.CustomerContactMatcher;
import trials.matcher.EntityMatcher;
import trials.matcher.Matcher;
import trials.matcher.MatchingRules;
import trials.config.IntegrationConfig;
import trials.sync.*;

import java.util.Iterator;

/**
 * @author cb-prasanna
 */
public class CBIntegrationFactory {


    public static Iterator<SyncSourceEntity> getSource(JSONObject config,
                                       IntegrationConfig.Integration integration,
                                       SourceEntityTypes sourceType) throws Exception {
        System.out.println("Obtaining the Source Information");
        Iterator<SyncSourceEntity> syncIterator= ChargebeeSyncSource.getBatch(integration,
                sourceType);
        return syncIterator;
    }

    public static SyncDestination getDestination(JSONObject config,
                                                 IntegrationConfig.Integration integration,
                                                 DestinationEntityTypes destinationType)
        throws IOException, URISyntaxException {
        System.out.println("Obtaining the Destination Information");
        switch (integration) {
            case HUBSPOT:
                return new HubspotSyncDestination(config.toString());
            default:
                return null;
        }
    }

    public static Mapper getMapper(IntegrationConfig.SyncItem syncItem, JSONObject config,
                                   Mapper.Type mapperType) {
        System.out.println("Resolving config and return the corresponding mapper");
        switch (mapperType) {
//            case DEFAULT: return null;
            default:
                return new EntityMapper(syncItem, new SystemConfig());
        }
    }

    public static Matcher getMatcher(JSONObject config, JSONObject systemConfig)
        throws IOException, URISyntaxException {
        System.out.println("Resolving config and return the corresponding matcher");
        return new CustomerContactMatcher(config.toString());
    }

    public static Iterator<SyncSource> getFieldRuleUpdater(JSONObject config, JSONObject systemConfig) {
        return null;
    }

    public static Iterator<SyncSource> getCustomFieldManager(JSONObject config, JSONObject systemConfig) {
        return null;
    }

}
