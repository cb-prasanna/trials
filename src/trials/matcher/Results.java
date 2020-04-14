package trials.matcher;
import trials.model.entity.SyncSourceEntity;

import java.util.List;

public class Results {
    List<SyncSourceEntity> matchedEntities;
    List<SyncSourceEntity> unMatchedEntities;
    List<SyncSourceEntity> NotProcessed;
    List<SyncSourceEntity> toBeCreated;
    List<SyncSourceEntity> toBeUpdated;
    List<SyncSourceEntity> errorEntities;
 }
