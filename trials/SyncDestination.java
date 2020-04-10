package trials;

import java.util.List;

/**
 * @author cb-prasanna
 */
public interface SyncDestination {

    static List<SyncDestination> findBatch( Field field, List<?> ids );

    static createBatch();
    static updateBatch();
    static createCustomFields();

    Integer getInt( String key );

    String getString( String key );

    Double getDouble( String key );

    void setInt( String key, Integer value );

    void setString( String key, String value );

    void setDouble( String key, Double value );
}
