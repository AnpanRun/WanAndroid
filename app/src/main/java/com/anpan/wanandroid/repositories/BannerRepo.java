package com.anpan.wanandroid.repositories;

import com.anpan.wanandroid.entities.BannerInfo;
import com.anpan.wanandroid.entities.ResponseModel;
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
 * 首页Banner
 * Created by AnpanRun on 2021/3/20.
 */
public class BannerRepo {
    private static volatile BannerRepo instance;

    private BannerRepo() {

    }

    public static BannerRepo getInstance() {
        if (instance == null) {
            synchronized (BannerRepo.class) {
                if (instance == null) {
                    instance = new BannerRepo();
                }
            }
        }
        return instance;
    }

    //获取Banner
    public void getBanners(CommonCallback callback) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        NetRequestInterface requestInterface = retrofit.create(NetRequestInterface.class);
        Call<ResponseModel<List<BannerInfo>>> call = requestInterface.getBannerInfo();
        call.enqueue(new Callback<ResponseModel<List<BannerInfo>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<BannerInfo>>> call, Response<ResponseModel<List<BannerInfo>>> response) {
                if (callback != null) {
                    if (response.body().getErrorCode().equals("0")) {
                        callback.onSuccess(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<BannerInfo>>> call, Throwable t) {

            }
        });
    }
}
