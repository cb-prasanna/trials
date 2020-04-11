package trials;

import org.json.simple.JSONObject;

import java.util.Iterator;

/**
 * @author cb-prasanna
 */
public class Factory {

     public SyncSource getSource(JSONObject config, JSONObject systemConfig){
         System.out.println("Obtaing the Source Information");
        return new ChargebeeSyncSource(new ApiDataFetchLayer());
    }

    public SyncDestination getDestination(JSONObject config, JSONObject systemConfig, String integration){
        System.out.println("Obtaing the Destination Information");
         switch (integration){
             case "Hubspot" : return new HubspotSyncDestination();
             default: return new HubspotSyncDestination();
         }
    }

    public MapperInterface getMapper(JSONObject config, JSONObject systemConfig){
        System.out.println("Resolving config and return the corresponding mapper");
        return new Mapper();
    }

    public MatcherInterface getMatcher(JSONObject config, JSONObject systemConfig){
        System.out.println("Resolving config and return the corresponding mapper");
        return new Matcher();
    }

    public Iterator<SyncSource> getFieldRuleUpdater(JSONObject config, JSONObject systemConfig){
        return null;
    }

    public Iterator<SyncSource> getCustomFieldManager(JSONObject config, JSONObject systemConfig){
        return null;
    }

}
