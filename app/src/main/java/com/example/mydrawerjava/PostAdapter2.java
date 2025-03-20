package com.example.mydrawerjava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PostAdapter2 extends RecyclerView.Adapter<PostAdapter2.PostViewHolder> {
    private List<Post> postList;

    private OnItemClickListener listener;



    public PostAdapter2(List<Post> postList, OnItemClickListener listener) {
        this.postList = postList;
        this.listener = listener;
    }
    public void setPostList(List<Post> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }


    public interface OnItemClickListener {
        void onItemClick(String itemText);
    }


    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.title.setText(post.getTitle());
        holder.body.setText(post.getBody());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(post.getTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return postList != null ? postList.size() : 0;
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView title, body;

        PostViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(android.R.id.text1);
            body = itemView.findViewById(android.R.id.text2);
        }
    }
}
