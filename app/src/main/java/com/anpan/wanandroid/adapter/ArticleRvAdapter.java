package com.anpan.wanandroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.anpan.wanandroid.R;
import com.anpan.wanandroid.entities.ArticleInfo;
import com.anpan.wanandroid.ui.WebViewActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 首页文章列表Adapter
 * Created by AnpanRun on 2021/1/30.
 */
public class ArticleRvAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private ArrayList<ArticleInfo> listItem;
    private Context context;
    private static final int ITEM_DATA = 0x1;
    private static final int ITEM_FOOTER = 0x2;

    public ArticleRvAdapter(Context context, ArrayList<ArticleInfo> listItem) {
        inflater = LayoutInflater.from(context);
        this.listItem = listItem;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = null;
        RecyclerView.ViewHolder viewHolder= null;
        switch (viewType) {
            case ITEM_DATA:
                itemView = inflater.inflate(R.layout.rv_articleinfo, null);
                itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                viewHolder = new DataViewHolder(itemView);
                break;
            case ITEM_FOOTER:
                itemView = inflater.inflate(R.layout.rv_item_footer, null);
                itemView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT));
                viewHolder = new FooterViewHolder(itemView);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DataViewHolder) {
            DataViewHolder vh = (DataViewHolder)holder;
            ArticleInfo articleInfo = listItem.get(position);
            if (articleInfo.getAuthor().equals("") || articleInfo.getAuthor()==null) {
                vh.mTvAuthor.setText(articleInfo.getShareUser());
            } else {
                vh.mTvAuthor.setText(articleInfo.getAuthor());
            }
            vh.mTvTitle.setText(articleInfo.getTitle());
            vh.mTvTime.setText(articleInfo.getNiceShareDate());
            vh.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context.startActivity(new Intent(context, WebViewActivity.class).putExtra("url", articleInfo.getLink()));
                }
            });
        }
    }


    @Override
    public int getItemViewType(int position) {
        //根据 Item 的 position 返回不同的 Viewtype
        if (position == (getItemCount() - 1)) {
            return ITEM_FOOTER;
        } else {
            return ITEM_DATA;
        }
    }

    @Override
    public int getItemCount() {
        return listItem.size() + 1;
    }

    class DataViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvTitle;
        private TextView mTvAuthor;
        private TextView mTvTime;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_articlinfo_title);
            mTvAuthor = itemView.findViewById(R.id.tv_articlinfo_author);
            mTvTime = itemView.findViewById(R.id.tv_articlinfo_time);
        }
    }


    class FooterViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar mPbLoading;
        private TextView mTvLoading;

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
            mPbLoading = itemView.findViewById(R.id.pb_item_footer_pb);
            mTvLoading = itemView.findViewById(R.id.tv_item_footer_loading);
        }
    }

    public void setListItem(ArrayList<ArticleInfo> listItem) {
        this.listItem = listItem;
    }
}
