package com.anpan.wanandroid.net;


import com.anpan.wanandroid.entities.ArticleInfo;
import com.anpan.wanandroid.entities.ArticleInfosResponse;
import com.anpan.wanandroid.entities.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 网络接口
 * Created by AnpanRun on 2021/1/31.
 */
public interface NetRequestInterface {
    @GET("article/list/{page}/json")
    Call<ResponseModel<ArticleInfosResponse>> getArticleInfos(@Path("page") int page);
}
