package com.tianfeng.zhongjiteaapp.json;

/**
 * Created by 田丰 on 2017/9/17.
 */

public  class Product {
    /**
     * unitName : 提
     * deportId : 0002
     * typeName : 生茶
     * type : 0001
     * tagName : 新茶上市
     * informationUrl : api/goods/content/4c00db720d614e1aad9270fa5581764c
     * imgUrl : static/img/def1.jpg
     * unit : 03
     * price : 10
     * deportName : 云南仓
     * id : 4c00db720d614e1aad9270fa5581764c
     * tag : 02
     * goodsName : 测试商品
     * introduction : 【品名】班盆庄园；
     【年份】2017年；
     【生熟】生茶（老班盆古树茶，云车间制茶）；
     【制作工艺】手工石磨饼；
     【规格】200克/饼，7饼/提，28饼/件；
     * createTime : 1505574541000
     */

    private String unitName;
    private String deportId;
    private String typeName;
    private String type;
    private String tagName;
    private String informationUrl;
    private String imgUrl;
    private String unit;
    private int price;
    private String deportName;
    private String id;
    private String tag;
    private String goodsName;
    private String introduction;
    private long createTime;

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getDeportId() {
        return deportId;
    }

    public void setDeportId(String deportId) {
        this.deportId = deportId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getInformationUrl() {
        return informationUrl;
    }

    public void setInformationUrl(String informationUrl) {
        this.informationUrl = informationUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDeportName() {
        return deportName;
    }

    public void setDeportName(String deportName) {
        this.deportName = deportName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}