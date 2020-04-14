package trials.matcher;

import trials.syncDestination.SyncDestination;
import trials.model.entity.SyncSourceEntity;
import trials.model.enums.Field;

import java.util.List;
import java.util.Map;


public class EntityMatcher extends Matcher {
    public EntityMatcher(SyncDestination syncDestination, MatchingRules matchingRules){
        this.syncDestination = syncDestination;
        this.matchingRules = matchingRules;
        results = new Results();
    }

    @Override
    public Results getMatcherResults(List<SyncSourceEntity> syncEntities) {

        Field matchingCriteria = matchingRules.getMatching_criteria();
        Boolean createFlag = matchingRules.getCreateOnNoMatch();
        Boolean errorFlag = matchingRules.getErrorOnNoMatch();
        Map<SyncSourceEntity, Boolean> map = syncDestination.findBatch(matchingCriteria, syncEntities);

        for(SyncSourceEntity entities : syncEntities){
            if(map.get(entities) != null){
                if(map.get(entities) == true){
                    results.matchedEntities.add(entities);
                }
                else{
                    if(createFlag == true){
                        results.toBeCreated.add(entities);
                    }
                    if(errorFlag == true){
                        results.errorEntities.add(entities);
                    }
                    results.unMatchedEntities.add(entities);
                }

            }
            else{
                results.NotProcessed.add(entities);
            }
        }
        return results;
    }
}
