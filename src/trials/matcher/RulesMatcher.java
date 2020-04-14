package trials.matcher;

import trials.model.enums.Field;

public interface RulesMatcher {
    Boolean getCreateOnNoMatch();

    Field getMatching_criteria();

    Boolean getErrorOnNoMatch();
}
