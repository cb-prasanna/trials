package trials;

import org.joda.time.LocalDate;
import org.json.simple.JSONObject;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * @author cb-prasanna
 */
public class Factory {

    static Iterator<SyncSource> createSource(JSONObject config, JSONObject systemConfig){
        String resource = "Customer";
        Timestamp startTime = new Timestamp(1234L);
        Timestamp endTime = new Timestamp(1234L);
        return new ChargebeeAPIIterator<>( resource, startTime, endTime );
    }

    static SyncDestination createDestination(JSONObject config, JSONObject systemConfig){
        return null;
    }

    static Iterator<SyncSource> createMatcher(JSONObject config, JSONObject systemConfig){
        return null;
    }

    static Iterator<SyncSource> createMapper(JSONObject config, JSONObject systemConfig){
        return null;
    }

    static Iterator<SyncSource> createFieldRuleUpdater(JSONObject config, JSONObject systemConfig){
        return null;
    }

    static Iterator<SyncSource> createCustomFieldManager(JSONObject config, JSONObject systemConfig){
        return null;
    }

}
