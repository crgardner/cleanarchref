package cleanarch.domain.users;

import org.apache.commons.lang3.StringUtils;

import cleanarch.common.exception.InvalidException;

public class NaivePasswordPolicy implements PasswordPolicy {

    @Override
    public void verify(String passwordCandidate) {
        if (StringUtils.isBlank(passwordCandidate)) {
            throw new InvalidException(UserMessageKey.INVALID_PASSWORD, "Invalid password found.");
        }
    }

}
