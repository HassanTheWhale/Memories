package site.thewhale.memories.other;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import site.thewhale.memories.objects.Post;
import site.thewhale.memories.objects.User;

public class Lists {
    private static FirebaseAuth mAuth;

    static FirebaseDatabase db = FirebaseDatabase.getInstance("https://memories-b188b-default-rtdb.firebaseio.com/");
    static DatabaseReference dbr = db.getReference();

    public static User currentUser;

    public static ArrayList<User> userArrayList = new ArrayList<User>();
    public static ArrayList<Post> postArrayList = new ArrayList<Post>();

    public static  void createList(String key) {
        mAuth = FirebaseAuth.getInstance();
        if (key.equals("users")) {
            userArrayList.clear();
            Query users = dbr.child("users");
            users.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot user : snapshot.getChildren()) {
                        User insertUser = user.getValue(User.class);
                        userArrayList.add(insertUser);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
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
