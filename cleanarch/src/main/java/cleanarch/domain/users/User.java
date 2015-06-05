package cleanarch.domain.users;

public class User {
    private String id;
    private String name;

    public User(String name) {
        this.name = name;
    }
    
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
