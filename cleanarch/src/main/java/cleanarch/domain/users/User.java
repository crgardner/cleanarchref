package cleanarch.domain.users;

public class User {
    private String id;
    private String name;
    private String password;

    public User(String name) {
        this.name = name;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void changePassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
}
