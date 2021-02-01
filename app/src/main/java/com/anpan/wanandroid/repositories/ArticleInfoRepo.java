package com.anpan.wanandroid.repositories;

import com.anpan.wanandroid.entities.ArticleInfosResponse;
import com.anpan.wanandroid.entities.ResponseModel;
import com.anpan.wanandroid.net.CommonCallback;
import com.anpan.wanandroid.net.NetRequestInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 首页文章列表数据仓库
 * Created by AnpanRun on 2021/1/30.
 */
public class ArticleInfoRepo {
    private static ArticleInfoRepo articleInfoRepo;

    public static ArticleInfoRepo getArticleInfoRepo() {
        if (articleInfoRepo == null) {
            synchronized (ArticleInfoRepo.class) {
                if (articleInfoRepo == null) {
                    articleInfoRepo = new ArticleInfoRepo();
                }
            }
        }
        return articleInfoRepo;
    }

    private ArticleInfoRepo() {

    }

    public void getArticleInfoFromServer(int page , CommonCallback callback) {
        if (page < 1) {
            page = 0;
        } else {
            page--;
        }
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://www.wanandroid.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        NetRequestInterface request = retrofit.create(NetRequestInterface.class);

        Call<ResponseModel<ArticleInfosResponse>> call = request.getArticleInfos(page);
        call.enqueue(new Callback<ResponseModel<ArticleInfosResponse>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArticleInfosResponse>> call, Response<ResponseModel<ArticleInfosResponse>> response) {
                if (callback != null) {
                    if (response.body().getErrorCode().equals("0")) {
                        callback.onSuccess(response.body().getData());
                    } else {
                        callback.onError(response.body().getErrorCode(), response.body().getErrorMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<ArticleInfosResponse>> call, Throwable t) {
                if (callback != null) {
                    callback.onError("404", t.getMessage());
                }
            }
        });
    }
}
