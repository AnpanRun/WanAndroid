package com.anpan.wanandroid.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anpan.wanandroid.R;
import com.anpan.wanandroid.adapter.ArticleRvAdapter;
import com.anpan.wanandroid.entities.ArticleInfo;
import com.anpan.wanandroid.vm.ArticleInfoListVM;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE;

public class IndexFragment extends Fragment {

    private ArticleInfoListVM aInfoListVm;
    private RecyclerView mRvArticleInfos;
    private ArticleRvAdapter articleRvAdapter;
    private int lastLoadDataItemPosition;
    private int page;

    public IndexFragment() {
    }

    public static IndexFragment newInstance() {
        IndexFragment fragment = new IndexFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
        observeLivaData();
        page = 1;
    }

    private void observeLivaData() {
        aInfoListVm.getLoadingLiveData().observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
            }
        });

        aInfoListVm.getArticleListLiveData().observe(getActivity(), new Observer<List<ArticleInfo>>() {
            @Override
            public void onChanged(List<ArticleInfo> articleInfos) {
                if (articleRvAdapter == null) {
                    articleRvAdapter = new ArticleRvAdapter(getActivity(), (ArrayList<ArticleInfo>) articleInfos);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    mRvArticleInfos.setLayoutManager(linearLayoutManager);
                    mRvArticleInfos.addItemDecoration(new DividerItemDecoration(getActivity(), linearLayoutManager.getOrientation()));
                    mRvArticleInfos.setAdapter(articleRvAdapter);
                } else {
                    articleRvAdapter.setListItem((ArrayList<ArticleInfo>) articleInfos);
                    articleRvAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initViewModel() {
        ViewModelProvider viewModelProvider = new ViewModelProvider(getActivity(), new ViewModelProvider.NewInstanceFactory());
        aInfoListVm = viewModelProvider.get(ArticleInfoListVM.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        mRvArticleInfos = view.findViewById(R.id.rv_articleInfos);
        mRvArticleInfos.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == SCROLL_STATE_IDLE &&
                        lastLoadDataItemPosition == (articleRvAdapter.getItemCount() - 1)) {
                    aInfoListVm.addToAInfoList(++page);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
                    lastLoadDataItemPosition = manager.findLastCompletelyVisibleItemPosition();
                }
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getData(page);
    }

    public void getData(int page) {
        aInfoListVm.getArticleInfos(page);
    }

}