package site.thewhale.memories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import site.thewhale.memories.adapters.PostAdapter;
import site.thewhale.memories.adapters.UserAdapter;
import site.thewhale.memories.objects.Post;
import site.thewhale.memories.objects.User;
import site.thewhale.memories.other.Lists;

public class OtherProfileActivity extends AppCompatActivity {
    FirebaseStorage storage;

    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);

        getWindow().setBackgroundDrawableResource(R.drawable.main_bg);

        Bundle b = getIntent().getExtras();
        User user = (User) b.getSerializable("user");

        CircleImageView img = findViewById(R.id.profileOther_image);
        TextView name = findViewById(R.id.profileOtherName);

        name.setText(user.getUsername());
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        storageReference.child("images/"+user.getImg()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(OtherProfileActivity.this).load(uri).into(img);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

        RecyclerView rView = findViewById(R.id.recyclerViewProfileOther);
        rView.setHasFixedSize(true);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(OtherProfileActivity.this);
        rView.setLayoutManager(lm);

        PostAdapter postAdapter = new PostAdapter(Lists.getPostArrayList(), OtherProfileActivity.this, 0);
        rView.setAdapter(postAdapter);

        ArrayList<Post> filterUsers = new ArrayList<Post>();
        for (Post post : Lists.getPostArrayList()) {
            if (post.getUsername().equals(user.getUsername())) {
                filterUsers.add(post);
            }
        }

        postAdapter.filterList(filterUsers);
    }
}