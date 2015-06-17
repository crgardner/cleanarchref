package cleanarch.domain.users;

public interface PasswordPolicy {

    void verify(String passwordCandidate);

}
