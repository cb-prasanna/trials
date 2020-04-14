package trials.syncDestination;

import trials.model.entity.SyncSourceEntity;
import trials.model.enums.Field;

import java.util.List;
import java.util.Map;

/**
 * @author cb-prasanna
 */
public interface SyncDestination {

    Map<SyncSourceEntity, Boolean> findBatch(Field field, List<?> ids );

    void createCustomFields();

    Integer getInt( String key );

    String getString( String key );

    Double getDouble( String key );

    void setInt( String key, Integer value );

    void setString( String key, String value );

    void setDouble( String key, Double value );
}
