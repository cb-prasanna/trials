package trials.matcher;

import trials.sync.FieldTypes;

public interface RulesMatcher {
    Boolean getCreateOnNoMatch();

    FieldTypes getMatching_criteria();

    Boolean getErrorOnNoMatch();
}
