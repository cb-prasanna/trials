package trials.matcher;

import trials.sync.FieldTypes;

public class MatchingRules implements RulesMatcher {

    private Boolean createOnNoMatch;
    private FieldTypes matchingCriteria;
    private Boolean errorOnNoMatch;

    public Boolean getCreateOnNoMatch() {
        return createOnNoMatch;
    }

    public FieldTypes getMatching_criteria() {
        return matchingCriteria;
    }

    public Boolean getErrorOnNoMatch() {
        return errorOnNoMatch;
    }
}
