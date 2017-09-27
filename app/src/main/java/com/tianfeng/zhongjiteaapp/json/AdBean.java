package com.tianfeng.zhongjiteaapp.json;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/27 0027.
 */
public  class AdBean implements Serializable{
    /**
     * id : bdebbaeaedee4c379e37f74cceb7b56a
     * title : 测试
     * imgUrl : /file/crop/image/goods/2017/img_1505315498535.bmp
     * url : https://www.baidu.com/
     * type : null
     * createTime : 1505488456000
     */

    private String id;
    private String title;
    private String imgUrl;
    private String url;
    private Object type;
    private long createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}