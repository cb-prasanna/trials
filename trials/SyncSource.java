package trials;

import java.util.List;
import java.util.Map;

/**
 * @author cb-prasanna
 */
public interface SyncSource {
    SyncEntities getBatch();

    Integer getInt( String key );

    String getString( String key );

    Double getDouble(String key );

    //TODO: Change from Boolean
    Map<String, Boolean> updateBatch(List<SyncSource> syncSources );
}
