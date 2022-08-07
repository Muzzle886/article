package ga.muzzle.pojo;

/**
 * @author himea
 */

public class User {
    private long id;
    private long tel;
    private String email;
    private String password;
    private String username;
    private String name;
    private int status;

    public User(long id, long tel, String email, String password, String username, String name, int status) {
        this.id = id;
        this.tel = tel;
        this.email = email;
        this.password = password;
        this.username = username;
        this.name = name;
        this.status = status;
    }

    public User(long tel, String email, String password, String username, String name) {
        this.tel = tel;
        this.email = email;
        this.password = password;
        this.username = username;
        this.name = name;
    }

    public User(long id, long tel, String email, String name) {
        this.id = id;
        this.tel = tel;
        this.email = email;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public long getTel() {
        return tel;
    }

    public void setTel(long tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = this.username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
