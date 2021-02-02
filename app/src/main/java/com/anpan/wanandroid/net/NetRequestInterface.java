package com.anpan.wanandroid.net;


import com.anpan.wanandroid.entities.ArticleInfosResponse;
import com.anpan.wanandroid.entities.ResponseModel;
import com.anpan.wanandroid.entities.WxAuthor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 网络接口
 * Created by AnpanRun on 2021/1/31.
 */
public interface NetRequestInterface {
    //首页文章列表
    @GET("article/list/{page}/json")
    Call<ResponseModel<ArticleInfosResponse>> getArticleInfos(@Path("page") int page);

    //公众号人物分类（列表)
    @GET("wxarticle/chapters/json")
    Call<ResponseModel<List<WxAuthor>>> getWxAuthors();

    //人物文章详细列表
    @GET("wxarticle/list/{id}/{page}/json")
    Call<ResponseModel<ArticleInfosResponse>> getWxArticleInfos(@Path("id") int id, @Path("page") int page);
}
