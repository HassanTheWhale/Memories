package site.thewhale.memories.adapters;


import android.content.Context;
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
    int kind = 0;

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
        if (kind == 0) {
            Picasso.with(contect).load(postsList.get(position).getImg()).into(((ViewHolder) holder).img);
            ((ViewHolder) holder).comment.setText(postsList.get(position).getComment());
            ((ViewHolder) holder).username.setText(postsList.get(position).getUsername());
            ((ViewHolder) holder).delete.setVisibility(View.GONE);
        } else if (kind == 1) {
            Picasso.with(contect).load(postsList.get(position).getImg()).into(((ViewHolder) holder).img);
            ((ViewHolder) holder).comment.setText(postsList.get(position).getComment());
            ((ViewHolder) holder).username.setText(postsList.get(position).getUsername());
        }
    }

    @Override
    public int getItemCount() {
        return postsList.size();
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
