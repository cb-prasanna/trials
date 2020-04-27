package trials.sync;

import com.chargebee.org.json.JSONArray;
import com.chargebee.org.json.JSONException;
import com.chargebee.org.json.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {
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


  public static void main(String[] args)
      throws URISyntaxException, HTTPPostException, IOException, AccessTokenExpired, HTTPGetException, JSONException {
    RestClient restClient = new RestClient();
    OAuth2 oauth = new OAuth2(
        "fe9bb31c-9200-482b-99d7-d93caa520b2f",
        "59ec6883-36fb-44fc-90f4-91eb3ddabaf9",
        "b969d80d-d5ed-4408-84b3-55ff03229511",
        "CN2GpNWbLhIFUYEAAEAYwv3JAyDm--AEKMGgDTIZAAyTBT4ogJ4MlJLqwUyOiB4H5h12fbp1yzoXAA4CxwAADIQDAAgAAAABAAAAAAAABhhCGQAMkwU-d4fTHCV2TokDhSy2PqKrTpdXpP4",
        "http://localhost:8080/oauth",
        "https://api.hubapi.com/oauth/v1/token",
        "2020-04-27T13:32:51.368848",
        "Site:prem");
        /*
        GENERATING
        {'properties': ['email', 'chargebeecustomerid'], 'idProperty': 'email', 'inputs': [{'id': 'test@123.com'}, {'id': 'a@b.com'}]}
         */
    //OAuth2 oauth = new OAuth2(authConfig);
    JSONObject body = new JSONObject(
        "{\"properties\":[\"email\",\"chargebeecustomerid\"],\"idProperty\":\"email\"}");
    var list = new JSONArray();
    List<String> ids = new ArrayList<>();
    ids.add("a@b.com");
    ids.add("test@123.com");
    for (var id : ids) {
      var item = new JSONObject();
      item.put("id", id);
      list.put(item);
    }
    body.put("inputs", list);
    String result = restClient
        .post(URI.create("https://api.hubapi.com/crm/v3/objects/contacts/batch/read"),
            oauth.getAuthHeaders(restClient), body.toString());
    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    APIResponse response = mapper.readValue(result, APIResponse.class);
    Map<String, SyncDestinationEntity> output = new HashMap<String, SyncDestinationEntity>();
    for (var contact : response.results) {
      SyncDestinationEntityBase entity = new SyncDestinationEntityBase(
          DestinationEntityTypes.Contacts);
      entity.setString("chargebeecustomerid", contact.properties.chargebeecustomerid);
      entity.setString("email", contact.properties.email);
      output.put(contact.properties.email, entity);
    }
    System.out.println(output.toString());
    System.out.println(oauth.dump());
  }
}

