package com.anpan.wanandroid.entities;

/**
 * 首页BannerInfo
 * Created by AnpanRun on 2021/3/20.
 */
public class BannerInfo {
    private String desc;

    private int id;

    private String imagePath;

    private int isVisible;

    private int order;

    private String title;

    private int type;

    private String url;

    public void setDesc(String desc){
        this.desc = desc;
    }
    public String getDesc(){
        return this.desc;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setImagePath(String imagePath){
        this.imagePath = imagePath;
    }
    public String getImagePath(){
        return this.imagePath;
    }
    public void setIsVisible(int isVisible){
        this.isVisible = isVisible;
    }
    public int getIsVisible(){
        return this.isVisible;
    }
    public void setOrder(int order){
        this.order = order;
    }
    public int getOrder(){
        return this.order;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setType(int type){
        this.type = type;
    }
    public int getType(){
        return this.type;
    }
    public void setUrl(String url){
        this.url = url;
    }
    public String getUrl(){
        return this.url;
    }
}
