package trials;

import org.json.simple.JSONObject;
import trials.matcher.EntityMatcher;
import trials.syncDestination.SyncDestination;
import trials.mapper.Mapper;
import trials.matcher.Matcher;
import trials.matcher.MatchingRules;
import trials.model.enums.Integrations;
import trials.model.enums.MappingTypes;
import trials.syncDestination.hubspot.HubspotSyncDestination;
import trials.syncSource.ChargebeeSyncSource;
import trials.syncSource.SyncSource;

import java.util.Iterator;

/**
 * @author cb-prasanna
 */
public class CBIntegrationFactory {
    public static SyncSource getSource(JSONObject config, JSONObject systemConfig){
        System.out.println("Obtaing the Source Information");
        return null;
    }

    public static SyncDestination getDestination(JSONObject config, JSONObject systemConfig, Integrations type){
        System.out.println("Obtaing the Destination Information");
        switch (type){
            case HUBSPOT: return new HubspotSyncDestination();
            default: return null;
        }
    }

    public static Mapper getMapper(JSONObject config, MappingTypes mapperTypes){
        System.out.println("Resolving config and return the corresponding mapper");
        switch (mapperTypes){
//            case DEFAULT: return null;
            default: return null;
        }
    }

    public static Matcher getMatcher(JSONObject config, JSONObject systemConfig){
        System.out.println("Resolving config and return the corresponding matcher");
        return new EntityMatcher(new HubspotSyncDestination(), new MatchingRules());
    }

    public static Iterator<SyncSource> getFieldRuleUpdater(JSONObject config, JSONObject systemConfig){
        return null;
    }

    public static Iterator<SyncSource> getCustomFieldManager(JSONObject config, JSONObject systemConfig){
        return null;
    }

}
