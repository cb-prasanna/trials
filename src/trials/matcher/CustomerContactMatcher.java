package trials.matcher;

import com.chargebee.org.json.JSONException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import trials.sync.AccessTokenExpired;
import trials.sync.DestinationEntityTypes;
import trials.sync.FieldTypes;
import trials.sync.HTTPPostException;
import trials.sync.SyncDestination;
import trials.sync.SyncDestinationEntity;
import trials.sync.SyncSourceEntity;

public class CustomerContactMatcher implements Matcher{
  String Config;
  boolean createOnNoMatch;
  String matchField;
  public CustomerContactMatcher(String Config) {
    this.Config = Config;
    this.createOnNoMatch = true;
    this.matchField = "email";
  }

  //TODO: default fields which needs to be fetched from Remote needs to be defined for each entity
  //TODO: this needs to be captured in config and somehow enforced in the framework.
  //TODO: This will help in use case where we do only update fields which are null/empty.
  @Override
  public Map<SyncSourceEntity, SyncDestinationEntity> findMatch(List<SyncSourceEntity> syncEntities,
      SyncDestination syncDestination)
      throws HTTPPostException, AccessTokenExpired, JSONException, IOException {
    FieldTypes fieldType = FieldTypes.valueOf(matchField);
    Map<SyncSourceEntity, SyncDestinationEntity> response = new HashMap<SyncSourceEntity, SyncDestinationEntity>();
    List<String> ids = new ArrayList<String>();
    switch (fieldType) {
      case EMAIL:
        for (SyncSourceEntity entity: syncEntities) {
          ids.add(entity.getString("email"));
        }
        Map<String, SyncDestinationEntity> foundEntities = syncDestination.findBatch(DestinationEntityTypes.Contacts, FieldTypes.EMAIL, ids);
        for (SyncSourceEntity entity: syncEntities) {
          String email = entity.getString("email");
          if (foundEntities.containsKey(email)) {
            response.put(entity, foundEntities.get(email));
          } else {
            SyncDestinationEntity destEntity = syncDestination.createEntity(DestinationEntityTypes.Contacts);
            destEntity.setNew();
            response.put(entity, destEntity);
          }
        }
        break;
    }
    return response;
  }
}
