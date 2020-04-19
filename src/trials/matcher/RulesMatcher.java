package trials.matcher;

import trials.sync.Field;

public interface RulesMatcher {
    Boolean getCreateOnNoMatch();

    Field getMatching_criteria();

    Boolean getErrorOnNoMatch();
}
