package cleanarch.domain.users;

import static org.assertj.core.api.Assertions.fail;

import org.junit.Before;
import org.junit.Test;

import cleanarch.common.exception.InvalidException;


public class NaivePasswordPolicyTest {
    private PasswordPolicy passwordPolicy;

    @Before
    public void setUp() {
        passwordPolicy = new NaivePasswordPolicy();
    }
    
    @Test
    public void determinesPasswordCandiateIsAcceptable() {
        try {
            passwordPolicy.verify("isagoodpassword");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    
    @Test(expected=InvalidException.class)
    public void detectsInvalidPasswordCandidate() throws Exception {
        passwordPolicy.verify("");
    }
}
