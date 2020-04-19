package trials.sync;

import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import trials.model.enums.DestinationEntityTypes;

/**
 * @author cb-prasanna
 */
public interface SyncDestination {

    Map<SyncDestinationEntity, Boolean> findBatch(
        DestinationEntityTypes entityType,
        FieldTypes field,
        List<String> ids);

    Map<String, Pair<String, Boolean>> createCustomFields(
        DestinationEntityTypes type,
        Map<String, String> fieldNames);

    Integer getInt( String key );

    String getString( String key );

    Double getDouble( String key );

    void setInt( String key, Integer value );

    void setString( String key, String value );

    void setDouble( String key, Double value );
}
