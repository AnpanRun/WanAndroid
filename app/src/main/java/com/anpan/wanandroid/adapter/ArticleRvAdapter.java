package com.anpan.wanandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anpan.wanandroid.R;
import com.anpan.wanandroid.entities.ArticleInfo;
import com.anpan.wanandroid.ui.WebViewActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by AnapanRun on 2021/1/30.
 */
public class ArticleRvAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private ArrayList<ArticleInfo> listItem;
    private Context context;

    public ArticleRvAdapter(Context context, ArrayList<ArticleInfo> listItem) {
        inflater = LayoutInflater.from(context);
        this.listItem = listItem;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = inflater.inflate(R.layout.rv_articleinfo, null);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("lbbb",position+"");
        ViewHolder vh = (ViewHolder) holder;
        ArticleInfo articleInfo = listItem.get(position);
        if (articleInfo.getAuthor().equals("")) {
            vh.mTvAuthor.setText(articleInfo.getShareUser());
        } else {
            vh.mTvAuthor.setText(articleInfo.getAuthor());
        }

        vh.mTvTitle.setText(articleInfo.getTitle());
        vh.mTvTime.setText(articleInfo.getNiceShareDate());
        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, WebViewActivity.class).putExtra("url",articleInfo.getLink()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvTitle;
        private TextView mTvAuthor;
        private TextView mTvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_articlinfo_title);
            mTvAuthor = itemView.findViewById(R.id.tv_articlinfo_author);
            mTvTime = itemView.findViewById(R.id.tv_articlinfo_time);
        }
    }
}
