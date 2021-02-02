package com.anpan.wanandroid.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anpan.wanandroid.R;
import com.anpan.wanandroid.adapter.ArticleRvAdapter;
import com.anpan.wanandroid.entities.ArticleInfo;
import com.anpan.wanandroid.vm.WxViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ArticleInfosFragment extends Fragment {


    private ArrayList<ArticleInfo> articleinfos;
    private RecyclerView mRvArticleInfos;
    private WxViewModel wxViewModel;
    private int id;

    public ArticleInfosFragment() {
        // Required empty public constructor
    }


    public static ArticleInfosFragment newInstance(int id) {
        ArticleInfosFragment fragment = new ArticleInfosFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt("id");
        wxViewModel = new WxViewModel();
        wxViewModel.getWxArticleInfoListLiveData().observe(getActivity(), new Observer<List<ArticleInfo>>() {
            @Override
            public void onChanged(List<ArticleInfo> articleInfos) {
                if (mRvArticleInfos.getAdapter() == null) {
                    ArticleRvAdapter articleRvAdapter = new ArticleRvAdapter(getActivity(), (ArrayList<ArticleInfo>) articleInfos);
                    mRvArticleInfos.setAdapter(articleRvAdapter);
                } else {
                    ((ArticleRvAdapter) mRvArticleInfos.getAdapter()).setListItem((ArrayList<ArticleInfo>) articleInfos);
                    mRvArticleInfos.getAdapter().notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_article_infos, container, false);
        mRvArticleInfos = rootView.findViewById(R.id.rv_fm_articleInfos);
        articleinfos = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRvArticleInfos.setLayoutManager(linearLayoutManager);
        mRvArticleInfos.addItemDecoration(new DividerItemDecoration(getActivity(), linearLayoutManager.getOrientation()));
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        wxViewModel.getWxArticleListFromService(id, 1);
    }
}