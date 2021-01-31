package com.anpan.wanandroid.vm;

import com.anpan.wanandroid.entities.ArticleInfo;
import com.anpan.wanandroid.entities.ArticleInfosResponse;
import com.anpan.wanandroid.entities.ResponseModel;
import com.anpan.wanandroid.net.CommonCallback;
import com.anpan.wanandroid.repositories.ArticleInfoRepo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * Created by AnpanRun on 2021/1/30.
 */
public class ArticleInfoListVM extends ViewModel {
    //文章列表信息
    private MutableLiveData<List<ArticleInfo>> articleInfoListLiveData;
    //进度条显示
    private MutableLiveData<Boolean> loadingLiveData;

    public ArticleInfoListVM() {
        articleInfoListLiveData = new MutableLiveData<>();
        loadingLiveData = new MutableLiveData<>();
    }

    public void getArticleInfos() {

        ArticleInfoRepo.getArticleInfoRepo().getArticleInfoFromServer(new CommonCallback<ArticleInfosResponse>() {

            @Override
            public void onSuccess(ArticleInfosResponse data) {
                articleInfoListLiveData.setValue(data.getDatas());
            }

            @Override
            public void onError(String resultCode, String resultMsg) {

            }
        });
    }

    public LiveData<List<ArticleInfo>> getArticleListLiveData() {
        return articleInfoListLiveData;
    }

    public LiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }

    public void setLoadingLiveData(MutableLiveData<Boolean> loadingLiveData) {
        this.loadingLiveData = loadingLiveData;
    }
}
