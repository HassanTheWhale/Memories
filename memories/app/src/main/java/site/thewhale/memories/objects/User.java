package site.thewhale.memories.objects;

import java.io.Serializable;

public class User implements Serializable {

    private String id;
    private String username;
    private String img ;
    private String email;
    private String name;
    private String password;

    public User(String id, String username, String email, String name, String password) {
        this.username = username;
        this.email = email;
        this.name = name;
        this.password = password;
        this.id = id;
    }

    public User(String id, String username, String img, String email, String name, String password) {
        this.username = username;
        this.img = img;
        this.email = email;
        this.name = name;
        this.password = password;
        this.id = id;
    }

    public User() {
        this.username = "UnKnown";
        this.img = "";
        this.email = "no@email.com";
        this.name = "N/A";
        this.password = "password";
        this.id = "id";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
