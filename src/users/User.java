package users;

public class User {
    private String username;
    private String password;
    private boolean isAdmin = false;

    public User(){}

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
