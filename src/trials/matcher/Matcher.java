package trials.matcher;

import trials.syncDestination.SyncDestination;
import java.util.List;
import trials.model.entity.SyncSourceEntity;

public abstract class Matcher {
    MatchingRules matchingRules;
    SyncDestination syncDestination;
    Results results;
    Results getMatcherResults(List<SyncSourceEntity> syncEntities){
        return null;
    }
}
