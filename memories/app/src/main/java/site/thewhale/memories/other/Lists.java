package site.thewhale.memories.other;

import java.util.ArrayList;

import site.thewhale.memories.R;
import site.thewhale.memories.fragments.objects.Post;
import site.thewhale.memories.fragments.objects.User;

public class Lists {

    private ArrayList<User> userArrayList = new ArrayList<User>();
    private ArrayList<Post> postArrayList = new ArrayList<Post>();

    public void createList(String key) {
        if (key.equals("users")) {

        } else if (key.equals("posts")) {
            postArrayList.add(new Post("1", R.drawable.bg, "TheWhale", 5, "This is an test 1"));
            postArrayList.add(new Post("2", R.drawable.bg, "TheWhale", 5, "This is an test 2"));
            postArrayList.add(new Post("3", R.drawable.bg, "TheWhale", 5, "This is an test 3"));
            postArrayList.add(new Post("4", R.drawable.bg, "TheWhale", 5, "This is an test 4"));
            postArrayList.add(new Post("5", R.drawable.bg, "TheWhale", 5, "This is an test 5"));
        }
    }

    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    public ArrayList<Post> getPostArrayList() {
        return postArrayList;
    }

    public void setPostArrayList(ArrayList<Post> postArrayList) {
        this.postArrayList = postArrayList;
    }
}
