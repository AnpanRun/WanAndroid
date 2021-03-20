package com.anpan.wanandroid.repositories;

import com.anpan.wanandroid.entities.ArticleInfosResponse;
import com.anpan.wanandroid.entities.ResponseModel;
import com.anpan.wanandroid.entities.WxAuthor;
import com.anpan.wanandroid.net.CommonCallback;
import com.anpan.wanandroid.net.NetRequestInterface;
import com.anpan.wanandroid.net.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 微信公众号数据仓库
 * Created by Anpan on 2021/2/2.
 */
public class WxRepo {
    //饿汉模式 单例
    private static volatile WxRepo wxRepo = new WxRepo();

    private WxRepo() {
    }

    public static WxRepo getInstance() {
        return wxRepo;
    }


    public void getWxAuthorFromService(CommonCallback<List<WxAuthor>> callback) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        NetRequestInterface requestInterface = retrofit.create(NetRequestInterface.class);
        Call<ResponseModel<List<WxAuthor>>> call = requestInterface.getWxAuthors();
        call.enqueue(new Callback<ResponseModel<List<WxAuthor>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<WxAuthor>>> call, Response<ResponseModel<List<WxAuthor>>> response) {
                ResponseModel<List<WxAuthor>> body = response.body();
                if (callback != null) {
                    if (body.getErrorCode().equals("0")) {
                        callback.onSuccess(body.getData());
                    } else {
                        callback.onError(body.getErrorCode(), body.getErrorMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<WxAuthor>>> call, Throwable t) {
                if (callback != null) {
                    callback.onError("404", t.getMessage());
                }
            }
        });
    }

    public void getWxArticleInfosFromService(int id, int page, CommonCallback<ArticleInfosResponse> callback) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        NetRequestInterface requestInterface = retrofit.create(NetRequestInterface.class);
        Call<ResponseModel<ArticleInfosResponse>> call = requestInterface.getWxArticleInfos(id, page);
        call.enqueue(new Callback<ResponseModel<ArticleInfosResponse>>() {
            @Override
            public void onResponse(Call<ResponseModel<ArticleInfosResponse>> call, Response<ResponseModel<ArticleInfosResponse>> response) {
                ResponseModel<ArticleInfosResponse> body = response.body();
                if (body.getErrorCode().equals("0")) {
                    if (callback != null) {
                        callback.onSuccess(body.getData());
                    } else {
                        callback.onError(body.getErrorCode(), body.getErrorMsg());
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
