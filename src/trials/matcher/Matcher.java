package trials.matcher;

import trials.sync.SyncDestination;
import trials.sync.SyncSourceEntity;

import java.util.List;

public abstract class Matcher {

    public enum Type {
        DEFAULT
    }

    MatchingRules matchingRules;
    SyncDestination syncDestination;
    Results results;

    Results getMatcherResults(List<SyncSourceEntity> syncEntities) {
        return null;
    }
}
