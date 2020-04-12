package trial;

import java.util.List;
import java.util.Map;

/**
 * @author cb-prasanna
 */
public interface SyncSource {

    //TODO: Change from Boolean
    Map<String, Boolean> updateBatch(List<SyncSource> syncSources);
}
