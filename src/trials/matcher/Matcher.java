package trials.matcher;

import com.chargebee.org.json.JSONException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import trials.sync.AccessTokenExpired;
import trials.sync.HTTPPostException;
import trials.sync.SyncDestination;
import trials.sync.SyncDestinationEntity;
import trials.sync.SyncSourceEntity;

public interface Matcher {
    Map<SyncSourceEntity, SyncDestinationEntity> findMatch(List<SyncSourceEntity> syncEntities, SyncDestination syncDestination)
        throws HTTPPostException, AccessTokenExpired, JSONException, IOException;
}
