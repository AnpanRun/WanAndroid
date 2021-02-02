package com.anpan.wanandroid.vm;

import com.anpan.wanandroid.entities.ArticleInfo;
import com.anpan.wanandroid.entities.ArticleInfosResponse;
import com.anpan.wanandroid.entities.WxAuthor;
import com.anpan.wanandroid.net.CommonCallback;
import com.anpan.wanandroid.repositories.WxRepo;

import java.util.List;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * 微信公众号VM
 * Created by AnpanRun on 2021/2/2.
 */
public class WxViewModel extends ViewModel {
    MutableLiveData<List<WxAuthor>> wxAuthorListLiveData;
    MutableLiveData<List<ArticleInfo>> wxArticleInfoListLiveData;

    public WxViewModel() {
        this.wxAuthorListLiveData = new MutableLiveData<List<WxAuthor>>();
        this.wxArticleInfoListLiveData = new MediatorLiveData<List<ArticleInfo>>();
    }

    public void getWxAuthorsFromService() {
        WxRepo.getInstance().getWxAuthorFromService(new CommonCallback() {
            @Override
            public void onSuccess(Object data) {
                wxAuthorListLiveData.setValue((List<WxAuthor>) data);
            }

            @Override
            public void onError(String resultCode, String resultMsg) {

            }
        });
    }

    public void getWxArticleListFromService(int id, int page) {
        if (page < 1) {
            page = 1;
        }
        WxRepo.getInstance().getWxArticleInfosFromService(id, page, new CommonCallback<ArticleInfosResponse>() {
            @Override
            public void onSuccess(ArticleInfosResponse data) {
                wxArticleInfoListLiveData.setValue(data.getDatas());
            }

            @Override
            public void onError(String resultCode, String resultMsg) {

            }
        });
    }

    public MutableLiveData<List<WxAuthor>> getWxAuthorListLiveData() {
        return wxAuthorListLiveData;
    }

    public MutableLiveData<List<ArticleInfo>> getWxArticleInfoListLiveData() {
        return wxArticleInfoListLiveData;
    }
}
