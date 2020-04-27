package trials.sync;

import com.chargebee.org.json.JSONException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

/**
 * @author cb-prasanna
 */
public interface SyncDestination {

    Map<String, SyncDestinationEntity> findBatch(
        DestinationEntityTypes entityType,
        FieldTypes field,
        List<String> ids) throws HTTPPostException, AccessTokenExpired, JSONException, IOException;

    Map<String, Pair<String, Boolean>> createCustomFields(
        DestinationEntityTypes type,
        Map<String, String> fieldNames);

}
