package trials.integ.chargebee;


import com.chargebee.ListResult;
import com.chargebee.filters.TimestampFilter;
import com.chargebee.internal.ListRequest;
import com.chargebee.models.Customer;
import com.chargebee.models.Subscription;
import com.chargebee.org.json.JSONArray;
import com.chargebee.org.json.JSONObject;
import trials.sync.SyncSourceEntity;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author cb-prasanna
 */
public class ChargebeeAPIIterator implements Iterator {
    private static final Map<String, ListRequest> RESOURCES = new HashMap<>();

    static {
        try {
            RESOURCES.put("subscription", Subscription.list().includeDeleted(true).limit(10));
            RESOURCES.put("customer", Customer.list().includeDeleted(true).limit(10));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final String resource;
    private ListRequest listRequest;
    private String offset = null;
    private boolean hasNext = true;
    private boolean completed = false;
    private Iterator<SyncSourceEntity> syncSourceEntityIterator;

    public ChargebeeAPIIterator(String resource) {
        this.resource = resource;
        this.listRequest = RESOURCES.get(resource);
        fill();
    }

    private static <U extends ListRequest> ListRequest getListRequest(ListRequest<U> listRequest, Timestamp start, Timestamp end, String offset, String param) {
        return new TimestampFilter<>(param, listRequest)
                .between(start, end)
                .offset(offset);
    }

    private void fill() {
        if (completed) {
            hasNext = false;
            return;
        }
        try {
            ListResult request = listRequest.offset(offset).request();
            JSONArray resourceData = request.jsonResponse().getJSONArray("list");
            if (resourceData.length() == 0) {
                hasNext = false;
                completed = true;
                return;
            }
            List<SyncSourceEntity> syncSourceEntities = new ArrayList<>();
            for (int i = 0; i < resourceData.length(); i++) {
                JSONObject resourceJSON = resourceData.getJSONObject(i).getJSONObject(resource);
                syncSourceEntities.add(new ChargebeeSyncSourceEntity(resource, resourceJSON));
            }

            syncSourceEntityIterator = syncSourceEntities.iterator();
            offset = request.nextOffset();
            if (offset == null) {
                completed = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public SyncSourceEntity next() {
        SyncSourceEntity entity = syncSourceEntityIterator.next();
        if (!syncSourceEntityIterator.hasNext()) {
            fill();
        }
        return entity;
    }
}
