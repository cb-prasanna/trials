package trials.integ.hubspot;

import com.chargebee.org.json.JSONArray;
import com.chargebee.org.json.JSONException;
import com.chargebee.org.json.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import trials.sync.AccessTokenExpired;
import trials.sync.DestinationEntityTypes;
import trials.sync.FieldTypes;
import trials.sync.HTTPPostException;
import trials.sync.OAuth2;
import trials.sync.RestClient;
import trials.sync.SyncDestinationEntity;
import trials.sync.SyncDestinationEntityBase;

public class HubspotSyncDestination implements trials.sync.SyncDestination {

    private String authConfig;
    private RestClient restClient;
    private OAuth2 oauth;
    public HubspotSyncDestination(String authConfig) throws IOException, URISyntaxException {
        this.authConfig = authConfig;
        RestClient restClient = new RestClient();
        OAuth2 oauth = new OAuth2(authConfig);
    }

    @Override
    public Map<String, SyncDestinationEntity> findBatch(
        DestinationEntityTypes entityType,
        FieldTypes input_type, List<String> ids)
        throws HTTPPostException, AccessTokenExpired, JSONException, IOException {
        switch (entityType) {
            case Contacts:
                switch (input_type) {
                    case EMAIL:
                        return findContactsByEmail(ids);
                }
                break;
            case Company:
                if (input_type != FieldTypes.ID) {
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

    private Map<String, SyncDestinationEntity> findContactsByIds(List<String> ids) {
        return null;
    }

    static class APIResponse {
        @JsonProperty("status")
        String status;
        @JsonProperty("results")
        List<APIResult> results;

        static class APIResult {
            @JsonProperty("id")
            String id;
            @JsonProperty("properties")
            HSProperties properties;
            @JsonProperty("createdAt")
            String createdAt;
            @JsonProperty("updatedAt")
            String updatedAt;
            @JsonProperty("archived")
            String archived;
        }

        static class HSProperties {
            @JsonProperty("chargebeecustomerid")
            String chargebeecustomerid;

            @JsonProperty("email")
            String email;

            @JsonProperty("hs_object_id")
            String id;
        }
    }

    private Map<String, SyncDestinationEntity> findContactsByEmail(List<String> ids)
        throws HTTPPostException, IOException, AccessTokenExpired, JSONException {
        /*
        GENERATING
        {'properties': ['email', 'chargebeecustomerid'],
         'idProperty': 'email',
          'inputs': [{'id': 'test@123.com'}, {'id': 'a@b.com'}]}
         */
        JSONObject body = new JSONObject("{\\\"properties\\\":[\\\"email\\\",\\\"chargebeecustomerid\\\"],\\\"idProperty\\\":\\\"email\\\"}");
        var list = new JSONArray();
        for (var id: ids) {
            var item = new JSONObject();
            item.put("id", id);
            list.put(item);
        }
        body.put("inputs", list);
        String result = restClient.post(URI.create("https://api.hubapi.com/crm/v3/objects/contacts/batch/read"),
            oauth.getAuthHeaders(restClient), body.toString());
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        APIResponse response = mapper.readValue(result, APIResponse.class);
        Map<String, SyncDestinationEntity> output = new HashMap<>();
        for (var contact: response.results) {
            SyncDestinationEntityBase entity = new SyncDestinationEntityBase(DestinationEntityTypes.Contacts);
            entity.setString("chargebeecustomerid", contact.properties.chargebeecustomerid);
            entity.setString("email", contact.properties.email);
            entity.setString("id", contact.id);
            output.put(contact.properties.email, entity);
        }
        return output;
    }

    private Map<SyncDestinationEntity, Boolean> findContactsByPhoneNumber(List<String> ids) {

        return null;
    }

    @Override
    public Map<String, Pair<String, Boolean>> createCustomFields(
        DestinationEntityTypes type,
        Map<String, String> fieldNames) {

        return null;
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