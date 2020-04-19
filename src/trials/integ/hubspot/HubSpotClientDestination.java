package trials.syncDestination.hubspot;
import static trials.model.enums.DestinationEntityTypes.*;

import com.sforce.soap.metadata.FileType;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import trials.model.enums.DestinationEntityTypes;
import trials.model.enums.FieldTypes;
import trials.syncDestination.SyncDestinationEntity;
import java.lang.UnsupportedOperationException;

class HubSpotClientDestination implements trials.syncDestination.SyncDestination {

    public HubSpotClientDestination() {
    }

    @Override
    public Map<SyncDestinationEntity, Boolean> findBatch(
        DestinationEntityTypes entityType,
        FieldTypes type, List<String> ids) {
        switch (entityType) {
            case Contacts:

                break;
            case Company:
                if (type != FieldTypes.ID) {
                    throw new UnsupportedOperationException("Company can be searched only by ID");
                }

                break;
            case Deal:
                break;
            default:
                break;
        }
        return null;
    }

    private Map<SyncDestinationEntity, Boolean> findContactsByIds(List<String> ids) {

    }

    private Map<SyncDestinationEntity, Boolean> findContactsByEmail(List<String> ids) {

    }

    private Map<SyncDestinationEntity, Boolean> findContactsByPhoneNumber(List<String> ids) {

    }

    @Override
    public Map<String, Pair<String, Boolean>> createCustomFields(
        DestinationEntityTypes type,
        Map<String, String> fieldNames) {

    }

    @Override
    public Integer getInt(String key) {
        return null;
    }

    @Override
    public String getString(String key) {
        return null;
    }

    @Override
    public Double getDouble(String key) {
        return null;
    }

    @Override
    public void setInt(String key, Integer value) {

    }

    @Override
    public void setString(String key, String value) {

    }

    @Override
    public void setDouble(String key, Double value) {

    }

    public static void main(String[] args) {

        HttpResponse<JsonNode> response = Unirest.post("http://httpbin.org/post")
            .header("accept", "application/json")
            .queryString("apiKey", "123")
            .field("parameter", "value")
            .field("firstname", "Gary")
            .asJson();
        System.out.println(response.getBody());
    }

}