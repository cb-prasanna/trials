package trials;

import java.sql.Timestamp;
import java.util.Iterator;

/**
 * @author cb-prasanna
 */
public class ChargebeeAPIIterator<T> implements Iterator<T> {
    public ChargebeeAPIIterator(String resource, Timestamp startTime, Timestamp endTime) {
//        new ChargebeeSyncSource();
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }
}
