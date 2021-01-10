package site.thewhale.memories.other;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import site.thewhale.memories.objects.Post;
import site.thewhale.memories.objects.User;

public class Lists {
    private static FirebaseAuth mAuth;

    public static ArrayList<User> userArrayList = new ArrayList<User>();
    public static  ArrayList<Post> postArrayList = new ArrayList<Post>();

    public static  void createList(String key) {
        mAuth = FirebaseAuth.getInstance();
        if (key.equals("users")) {
            userArrayList.clear();
            userArrayList.add(new User("aa", "TheWhale","https://i.pinimg.com/originals/47/0a/19/470a19a36904fe200610cc1f41eb00d9.jpg", "hassan@thewhale.site", "Hassan Khalaf", "123456789"));
            userArrayList.add(new User("aa", "TheWhale2","https://i.pinimg.com/originals/47/0a/19/470a19a36904fe200610cc1f41eb00d9.jpg", "hassan@thewhale.site", "Hassan Khalaf", "123456789"));
            userArrayList.add(new User("aa", "TheWhale3","https://i.pinimg.com/originals/47/0a/19/470a19a36904fe200610cc1f41eb00d9.jpg", "hassan@thewhale.site", "Hassan Khalaf", "123456789"));
            userArrayList.add(new User("aa", "TheWhale4","https://i.pinimg.com/originals/47/0a/19/470a19a36904fe200610cc1f41eb00d9.jpg", "hassan@thewhale.site", "Hassan Khalaf", "123456789"));
            userArrayList.add(new User("aa", "TheWhale5","https://i.pinimg.com/originals/47/0a/19/470a19a36904fe200610cc1f41eb00d9.jpg", "hassan@thewhale.site", "Hassan Khalaf", "123456789"));
            userArrayList.add(new User("aa", "TheWhale6","https://i.pinimg.com/originals/47/0a/19/470a19a36904fe200610cc1f41eb00d9.jpg", "hassan@thewhale.site", "Hassan Khalaf", "123456789"));
            userArrayList.add(new User("aa", "TheWhale7","https://i.pinimg.com/originals/47/0a/19/470a19a36904fe200610cc1f41eb00d9.jpg", "hassan@thewhale.site", "Hassan Khalaf", "123456789"));
            userArrayList.add(new User("aa", "TheWhale8","https://i.pinimg.com/originals/47/0a/19/470a19a36904fe200610cc1f41eb00d9.jpg", "hassan@thewhale.site", "Hassan Khalaf", "123456789"));
            userArrayList.add(new User("aa", "TheWhale9","https://i.pinimg.com/originals/47/0a/19/470a19a36904fe200610cc1f41eb00d9.jpg", "hassan@thewhale.site", "Hassan Khalaf", "123456789"));
            userArrayList.add(new User("aa", "TheWhale10","https://i.pinimg.com/originals/47/0a/19/470a19a36904fe200610cc1f41eb00d9.jpg", "hassan@thewhale.site", "Hassan Khalaf", "123456789"));
            userArrayList.add(new User("aa", "TheWhale11","https://i.pinimg.com/originals/47/0a/19/470a19a36904fe200610cc1f41eb00d9.jpg", "hassan@thewhale.site", "Hassan Khalaf", "123456789"));
            userArrayList.add(new User("aa", "TheWhale12","https://i.pinimg.com/originals/47/0a/19/470a19a36904fe200610cc1f41eb00d9.jpg", "hassan@thewhale.site", "Hassan Khalaf", "123456789"));
        } else if (key.equals("posts")) {
            postArrayList.clear();
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
