package site.thewhale.memories.objects;

import java.io.Serializable;

public class Post implements Serializable {
    //attributes
    private String img;
    private String username;
    private int likes;
    private String comment;



    public Post(String img, String username, int likes, String comment) {
        this.img = img;
        this.username = username;
        this.likes = likes;
        this.comment = comment;
    }

    public Post() {

    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
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
