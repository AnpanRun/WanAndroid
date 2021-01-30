package com.anpan.wanandroid.repositories;

import com.anpan.wanandroid.entities.ArticleInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AnpanRun on 2021/1/30.
 */
public class ArticleInfoRepo {
    private static ArticleInfoRepo articleInfoRepo;

    public static ArticleInfoRepo getArticleInfoRepo() {
        if(articleInfoRepo == null){
           synchronized (ArticleInfoRepo.class){
               if(articleInfoRepo == null){
                    articleInfoRepo = new ArticleInfoRepo();
               }
           }
        }
        return articleInfoRepo;
    }

    private ArticleInfoRepo(){

    }

    public List<ArticleInfo> getArticleInfoFromServer(){
        List<ArticleInfo> articleInfos = new ArrayList<>();
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setTitle("测试文章");
        articleInfo.setAuthor("Anpan");
        articleInfo.setPublishTime(11133);
        articleInfos.add(articleInfo);
        ArticleInfo articleInfo2 = new ArticleInfo();
        articleInfo2.setTitle("测试文章2");
        articleInfo2.setAuthor("Anpan2");
        articleInfo2.setPublishTime(11133);
        articleInfos.add(articleInfo2);
        return articleInfos;
    }
}
