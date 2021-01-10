package site.thewhale.memories.adapters;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import site.thewhale.memories.OtherProfileActivity;
import site.thewhale.memories.R;
import site.thewhale.memories.objects.User;
import site.thewhale.memories.other.Lists;

public class UserAdapter extends RecyclerView.Adapter {

    ArrayList<User> userList;
    Context contect;
    FirebaseStorage storage;

    StorageReference storageReference;

    public UserAdapter(ArrayList<User> userList, Context contect) {
        this.userList = userList;
        this.contect = contect;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Connecting to special recycle view xml file;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_recycle_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerView.ViewHolder holder, int position) {
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        storageReference.child("images/"+userList.get(position).getImg()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(contect).load(uri).into(((ViewHolder) holder).img);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
            ((ViewHolder) holder).username.setText(userList.get(position).getUsername());
            ((ViewHolder) holder).view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(contect, OtherProfileActivity.class);
                    i.putExtra("user", userList.get(position));
                    contect.startActivity(i);
                }
            });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void filterList(ArrayList<User> filterList) {
        userList = filterList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView username;
        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            img = itemView.findViewById(R.id.searchProfileImg);
            username = itemView.findViewById(R.id.searchUsername);
        }
    }
}
