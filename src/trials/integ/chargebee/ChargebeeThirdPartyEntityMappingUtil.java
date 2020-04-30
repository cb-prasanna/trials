package trials.integ.chargebee;

import com.chargebee.ListResult;
import com.chargebee.filters.TimestampFilter;
import com.chargebee.internal.ListRequest;
import com.chargebee.internal.Request;
import com.chargebee.models.ThirdPartyEntityMapping;
import com.chargebee.org.json.JSONObject;
import trials.sync.SourceEntityTypes;
import trials.sync.SyncSourceEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author cb-revathy
 * This class is the access point for ThirdPartyEntityMapping
 */


public class ChargebeeThirdPartyEntityMappingUtil implements Iterator {

    private final String resource = "third_party_entity_mapping";
    private final ThirdPartyEntityMapping.EntityType entityType;
    private final String integrationName;
    private ListRequest listRequest;
    private String offset = null;
    private boolean hasNext = true;
    private boolean completed = false;
    private Iterator<SyncSourceEntity> syncSourceEntityIterator;

    public ChargebeeThirdPartyEntityMappingUtil(SourceEntityTypes entityType,
                                                String integrationName) {
        this.entityType = getEntityType(entityType);
        this.integrationName = integrationName;
    }

    private static <U extends ListRequest> ListRequest getListRequest(ListRequest<U> listRequest, Timestamp start, Timestamp end, String offset, String param) {
        return new TimestampFilter<>(param, listRequest)
                .between(start, end)
                .offset(offset);
    }

    public static ThirdPartyEntityMapping.EntityType getEntityType(SourceEntityTypes entityTitle) {
        switch (entityTitle) {
            case Customer:
                return ThirdPartyEntityMapping.EntityType.CUSTOMER;
            case Subscription:
                return ThirdPartyEntityMapping.EntityType.SUBSCRIPTION;
            default:
                return null;
        }
    }

    public void updateMapping(String entityId, JSONObject mappingMeta) throws Exception {
        Request req = ThirdPartyEntityMapping
                .updateEntity()
                .entityId(entityId)
                .entityType(entityType)
                .integrationName(integrationName)
                .status(ThirdPartyEntityMapping.Status.TO_BE_PICKED)
                .mappingMeta(mappingMeta);
        ThirdPartyEntityMapping request = req.request().thirdPartyEntityMapping();

    }

    //Overload
    public void updateMapping(String entityId, ThirdPartyEntityMapping.Status status,
                              String errorMessage, String externalId) throws Exception {
        Request req = ThirdPartyEntityMapping
                .updateEntity()
                .entityId(entityId)
                .entityType(entityType)
                .thirdPartyEntityId(externalId)
                .integrationName(integrationName)
                .status(status)
                .errorMessage(errorMessage);
        ThirdPartyEntityMapping request = req.request().thirdPartyEntityMapping();
    }

    public void getDataForSync(int batchSize) throws Exception {
        ThirdPartyEntityMapping.ListAllRequest req = ThirdPartyEntityMapping.listAll()
                .integrationName(integrationName)
                .status().in(ThirdPartyEntityMapping.Status.TO_BE_PICKED,
                        ThirdPartyEntityMapping.Status.CREATE_FAILED,
                        ThirdPartyEntityMapping.Status.UPDATE_FAILED,
                        ThirdPartyEntityMapping.Status.STOPPED,
                        ThirdPartyEntityMapping.Status.MISMATCH,
                        ThirdPartyEntityMapping.Status.PARTIALLY_SYNCED)
                .limit(batchSize);
        this.listRequest = req;
        fill();
    }

    public void getIterator() {
        fill();
    }

    private void fill() {
        if (completed) {
            hasNext = false;
            return;
        }
        try {
            ListResult request = listRequest.offset(offset).request();

            if (request.size() == 0) {
                hasNext = false;
                completed = true;
                return;
            }
            List<SyncSourceEntity> syncSourceEntities = new ArrayList<>();
            for (int i = 0; i < request.size(); i++) {
                JSONObject resourceJSON = request.get(i).thirdPartyEntityMapping().mappingMeta();
                System.out.println(resourceJSON);
                syncSourceEntities.add(new ChargebeeSyncSourceEntity(entityType.toString(),
                        resourceJSON));
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
