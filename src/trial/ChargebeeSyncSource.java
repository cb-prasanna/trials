package trial;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author cb-prasanna
 */
public class ChargebeeSyncSource implements SyncSource {

    Iterator<SyncSourceEntity> iterator;

    public ChargebeeSyncSource( Iterator<SyncSourceEntity> iterator ) {
        this.iterator = iterator;
    }

    SyncSourceEntity getNext(){
        return iterator.next();
    }

    @Override
    public Map<String, Boolean> updateBatch(List<SyncSource> syncSources) {
        return null;
    }
}
