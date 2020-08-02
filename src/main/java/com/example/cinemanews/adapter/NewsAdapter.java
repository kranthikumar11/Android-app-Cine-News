package com.example.cinemanews.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemanews.R;
import com.example.cinemanews.WebActivity;
import com.example.cinemanews.news.Article;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
    private Context context;
    private Article[] data;

    public NewsAdapter(Context context, Article[] data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.display, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        final Article article=data[position];
        holder.textView.setText(article.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browse=new Intent(context,WebActivity.class);
                browse.putExtra("URL", article.getUrl());
                context.startActivity(browse);

            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class NewsHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text);
        }
    }
}
