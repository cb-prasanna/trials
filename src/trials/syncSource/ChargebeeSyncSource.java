package trials.syncSource;

import trials.model.entity.SyncSourceEntity;

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

    public Map<String, Boolean> updateBatch(List<SyncSource> syncSources) {
        return null;
    }
}
