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
            Query posts = dbr.child("posts");
            posts.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot post : snapshot.getChildren()) {
                        Post insertPost = post.getValue(Post.class);
                        postArrayList.add(insertPost);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
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
