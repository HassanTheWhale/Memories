package site.thewhale.memories.other;

import java.util.ArrayList;

import site.thewhale.memories.objects.Post;
import site.thewhale.memories.objects.User;

public class Lists {

    public static ArrayList<User> userArrayList = new ArrayList<User>();
    public static  ArrayList<Post> postArrayList = new ArrayList<Post>();

    public static  void createList(String key) {
        if (key.equals("users")) {

        } else if (key.equals("posts")) {
            postArrayList.add(new Post("1", "https://i.pinimg.com/originals/47/0a/19/470a19a36904fe200610cc1f41eb00d9.jpg", "TheWhale", 5, "This is an test 1"));
            postArrayList.add(new Post("2", "https://cdn.discordapp.com/attachments/784854063413329981/790031320938971146/KuwaitCodesWallpaper.png", "TheWhale", 5, "This is an test 2"));
            postArrayList.add(new Post("3", "https://cdn.discordapp.com/attachments/784854063413329981/790031320938971146/KuwaitCodesWallpaper.png", "TheWhale", 5, "This is an test 3"));
            postArrayList.add(new Post("4", "https://cdn.discordapp.com/attachments/784854063413329981/790031320938971146/KuwaitCodesWallpaper.png", "TheWhale", 5, "This is an test 4"));
            postArrayList.add(new Post("5", "https://cdn.discordapp.com/attachments/784854063413329981/790031320938971146/KuwaitCodesWallpaper.png", "TheWhale", 5, "This is an test 5"));
        }
    }

    public static ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    public static void setUserArrayList(ArrayList<User> userArrayList1) {
        userArrayList = userArrayList1;
    }

    public static ArrayList<Post> getPostArrayList() {
        return postArrayList;
    }

    public static void setPostArrayList(ArrayList<Post> postArrayList2) {
        postArrayList = postArrayList2;
    }
}
