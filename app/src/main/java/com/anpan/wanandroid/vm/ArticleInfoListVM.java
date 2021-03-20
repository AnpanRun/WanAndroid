package com.anpan.wanandroid.vm;

import android.util.Log;

import com.anpan.wanandroid.entities.ArticleInfo;
import com.anpan.wanandroid.entities.ArticleInfosResponse;
import com.anpan.wanandroid.entities.BannerInfo;
import com.anpan.wanandroid.net.CommonCallback;
import com.anpan.wanandroid.repositories.ArticleInfoRepo;
import com.anpan.wanandroid.repositories.BannerRepo;

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
    //首页Banner
    private MutableLiveData<List<BannerInfo>> bannerLiveData;

    public ArticleInfoListVM() {
        articleInfoListLiveData = new MutableLiveData<>();
        loadingLiveData = new MutableLiveData<>();
        bannerLiveData = new MutableLiveData<>();
    }

    public void getArticleInfos(int page ,boolean isAdd) {
        Log.d("AnpanRun","getArticleInfos");

        ArticleInfoRepo.getInstance().getArticleInfoFromServer(page ,new CommonCallback<ArticleInfosResponse>() {

            @Override
            public void onSuccess(ArticleInfosResponse data) {
                if(isAdd) {
                    ArrayList<ArticleInfo> articleInfoArrayList = (ArrayList<ArticleInfo>) articleInfoListLiveData.getValue();
                    articleInfoArrayList.addAll(data.getDatas());
                    articleInfoListLiveData.setValue(articleInfoArrayList);
                    Log.d("AnpanRun","getArticleInfos  onSuccess add");
                }else {
                    articleInfoListLiveData.setValue(data.getDatas());
                    Log.d("AnpanRun","getArticleInfos  onSuccess");
                }
            }

            @Override
            public void onError(String resultCode, String resultMsg) {
                Log.d("AnpanRun","getArticleInfos  Onerror");
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

    /**
     * 获取首页Bannaer
     */
    public void getBanner(){
        BannerRepo.getInstance().getBanners(new CommonCallback<List<BannerInfo>>() {
            @Override
            public void onSuccess(List<BannerInfo> data) {
                bannerLiveData.setValue(data);
            }

            @Override
            public void onError(String resultCode, String resultMsg) {
                Log.d("AnpanRun","getBanners  Onerror");
            }
        });
    }

    public MutableLiveData<List<BannerInfo>> getBannerLiveData() {
        return bannerLiveData;
    }

    public void setBannerLiveData(MutableLiveData<List<BannerInfo>> bannerLiveData) {
        this.bannerLiveData = bannerLiveData;
    }
}
