package site.thewhale.memories.fragments.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import site.thewhale.memories.EditActivity;
import site.thewhale.memories.LoginActivity;
import site.thewhale.memories.MainActivity;
import site.thewhale.memories.R;
import site.thewhale.memories.ZeroPointActivity;
import site.thewhale.memories.adapters.PostAdapter;
import site.thewhale.memories.objects.Post;
import site.thewhale.memories.other.Lists;

public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }
    FirebaseStorage storage;

    StorageReference storageReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        CircleImageView img = view.findViewById(R.id.profile_image);
        storageReference.child("images/"+Lists.currentUser.getImg()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(view.getContext()).load(uri).into(img);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });

        TextView name = view.findViewById(R.id.ProfileName);
        name.setText(Lists.currentUser.getUsername());

        RecyclerView rView = view.findViewById(R.id.recyclerViewProfile);
        rView.setHasFixedSize(true);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(view.getContext());
        rView.setLayoutManager(lm);

        PostAdapter postAdapter = new PostAdapter(Lists.getPostArrayList(), view.getContext(), 1);
        rView.setAdapter(postAdapter);

        ArrayList<Post> filterUsers = new ArrayList<Post>();
        for (Post post : Lists.getPostArrayList()) {
            if (post.getUsername().equals(Lists.currentUser.getUsername())) {
                filterUsers.add(post);
            }
        }
        postAdapter.filterList(filterUsers);

        Button edit = view.findViewById(R.id.profileEdit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent((MainActivity) (getActivity()), EditActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}