package site.thewhale.memories.adapters;


import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import site.thewhale.memories.R;
import site.thewhale.memories.objects.Post;
import site.thewhale.memories.objects.User;

public class PostAdapter extends RecyclerView.Adapter {

    ArrayList<Post> postsList;
    Context contect;
    int kind = 0;
    FirebaseStorage storage;
    StorageReference storageReference;

    public PostAdapter(ArrayList<Post> postsList, Context contect, int kind) {
        this.postsList = postsList;
        this.contect = contect;
        this.kind = kind;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Connecting to special recycle view xml file;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_recycle_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerView.ViewHolder holder, int position) {
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        storageReference.child("images2/"+postsList.get(position).getImg()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(contect).load(uri).into(((PostAdapter.ViewHolder) holder).img);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
        ((ViewHolder) holder).comment.setText(postsList.get(position).getComment());
        ((ViewHolder) holder).username.setText(postsList.get(position).getUsername());
//        if (kind == 0)
            ((ViewHolder) holder).delete.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public void filterList(ArrayList<Post> filterList) {
        postsList = filterList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public ImageView delete;
        public TextView username;
        public TextView comment;
        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            img = itemView.findViewById(R.id.postPic);
            username = itemView.findViewById(R.id.postUsername);
            comment = itemView.findViewById(R.id.postComment);
            delete = itemView.findViewById(R.id.postDelete);
        }
    }
}
