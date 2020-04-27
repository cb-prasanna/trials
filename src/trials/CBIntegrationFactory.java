package trials;

import trials.integ.hubspot.HubspotSyncDestination;
import com.chargebee.org.json.JSONObject;
import trials.matcher.EntityMatcher;
import trials.matcher.Matcher;
import trials.matcher.MatchingRules;
import trials.config.IntegrationConfig;
import trials.sync.Mapper;
import trials.sync.SyncDestination;
import trials.sync.SyncSource;

import java.util.Iterator;

/**
 * @author cb-prasanna
 */
public class CBIntegrationFactory {
    public static SyncSource getSource(JSONObject config, JSONObject systemConfig) {
        System.out.println("Obtaing the Source Information");
        return null;
    }

    public static SyncDestination getDestination(JSONObject config, JSONObject systemConfig, IntegrationConfig.Integration integration) {
        System.out.println("Obtaing the Destination Information");
        switch (integration) {
            case HUBSPOT:
                return new HubspotSyncDestination(config.toString());
            default:
                return null;
        }
    }

    public static Mapper getMapper(JSONObject config, Mapper.Type mapperType) {
        System.out.println("Resolving config and return the corresponding mapper");
        switch (mapperType) {
//            case DEFAULT: return null;
            default:
                return null;
        }
    }

    public static Matcher getMatcher(JSONObject config, JSONObject systemConfig) {
        System.out.println("Resolving config and return the corresponding matcher");
        return new EntityMatcher(new HubspotSyncDestination(config.toString()), new MatchingRules());
    }

    public static Iterator<SyncSource> getFieldRuleUpdater(JSONObject config, JSONObject systemConfig) {
        return null;
    }

    public static Iterator<SyncSource> getCustomFieldManager(JSONObject config, JSONObject systemConfig) {
        return null;
    }

}
