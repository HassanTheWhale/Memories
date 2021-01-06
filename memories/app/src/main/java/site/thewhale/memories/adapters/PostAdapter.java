package site.thewhale.memories.adapters;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import site.thewhale.memories.R;
import site.thewhale.memories.objects.Post;

public class PostAdapter extends RecyclerView.Adapter {

    ArrayList<Post> postsList;
    Context contect;

    public PostAdapter(ArrayList<Post> postsList, Context contect) {
        this.postsList = postsList;
        this.contect = contect;
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
        Picasso.with(contect).load(postsList.get(position).getImg()).into(((ViewHolder) holder).img);
        ((ViewHolder) holder).comment.setText(postsList.get(position).getComment());
        ((ViewHolder) holder).likes.setText(String.valueOf(postsList.get(position).getLikes()));
        ((ViewHolder) holder).username.setText(postsList.get(position).getUsername());
//        ((ViewHolder) holder).img.setImageResource(R.drawable.heart);
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img;
        public TextView username;
        public TextView likes;
        public TextView comment;
        public View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            img = itemView.findViewById(R.id.postPic);
            username = itemView.findViewById(R.id.postUsername);
            likes = itemView.findViewById(R.id.postLike);
            comment = itemView.findViewById(R.id.postComment);
        }
    }
}
