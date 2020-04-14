package trials.matcher;

import trials.model.enums.Field;

public class MatchingRules implements RulesMatcher {

    private Boolean createOnNoMatch;
    private Field matchingCriteria;
    private Boolean errorOnNoMatch;

    public Boolean getCreateOnNoMatch() {
        return createOnNoMatch;
    }

    public Field getMatching_criteria() {
        return matchingCriteria;
    }

    public Boolean getErrorOnNoMatch() {
        return errorOnNoMatch;
    }
}
