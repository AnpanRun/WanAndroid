package com.anpan.wanandroid.vm;

import com.anpan.wanandroid.entities.ArticleInfo;
import com.anpan.wanandroid.entities.ArticleInfosResponse;
import com.anpan.wanandroid.net.CommonCallback;
import com.anpan.wanandroid.repositories.ArticleInfoRepo;

import java.util.ArrayList;
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

    public void getArticleInfos(int page ,boolean isAdd) {

        ArticleInfoRepo.getArticleInfoRepo().getArticleInfoFromServer(page ,new CommonCallback<ArticleInfosResponse>() {

            @Override
            public void onSuccess(ArticleInfosResponse data) {
                if(isAdd) {
                    ArrayList<ArticleInfo> articleInfoArrayList = (ArrayList<ArticleInfo>) articleInfoListLiveData.getValue();
                    articleInfoArrayList.addAll(data.getDatas());
                    articleInfoListLiveData.setValue(articleInfoArrayList);
                }else {
                    articleInfoListLiveData.setValue(data.getDatas());
                }
            }

            @Override
            public void onError(String resultCode, String resultMsg) {

            }
        });
    }

    /**
     * 获取第page页的文字列表
     * @param page
     */
    public void getArticleInfos(int page) {
        getArticleInfos(page , false);
    }

    /**
     * 把第page页加入到文章列表中
     * @param page
     */
    public void addToAInfoList(int page){
        getArticleInfos(page , true);
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
