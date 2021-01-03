package site.thewhale.memories.fragments.objects;

import java.io.Serializable;

public class Post implements Serializable {
    //attributes
    private String ID;
    private int img;
    private String username;
    private int likes;
    private String comment;

    public String getID() {
        return ID;
    }

    public Post(String ID, int img, String username, int likes, String comment) {
        this.ID = ID;
        this.img = img;
        this.username = username;
        this.likes = likes;
        this.comment = comment;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
