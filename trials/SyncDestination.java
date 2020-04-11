package trials;

import java.util.List;

/**
 * @author cb-prasanna
 */
public interface SyncDestination<T> {

     List<T> findBatch( Field field, List<?> ids );

    void updateBatch(SyncEntities syncEntities);

    void createCustomFields();

    Integer getInt( String key );

    String getString( String key );

    Double getDouble( String key );

    void setInt( String key, Integer value );

    void setString( String key, String value );

    void setDouble( String key, Double value );

    class T {
    }
}
