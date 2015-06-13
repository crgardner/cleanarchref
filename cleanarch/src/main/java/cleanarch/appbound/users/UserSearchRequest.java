package cleanarch.appbound.users;


public class UserSearchRequest {
    private final String userId;

    public UserSearchRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

}
