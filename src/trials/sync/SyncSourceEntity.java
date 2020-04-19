package trials.sync;

/**
 * @author cb-prasanna
 */
public interface SyncSourceEntity {

    String getType();

    Integer getInt(String key);

    String getString(String key);

    Double getDouble(String key);

    String getResource();

    boolean getBoolean(String src_name);
}
