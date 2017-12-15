package com.tianfeng.zhongjiteaapp.json;

import com.tianfeng.zhongjiteaapp.utils.StringUtils;

import java.io.Serializable;

/**
 * Created by 田丰 on 2017/9/20.
 */

public class OrderBean implements Serializable {


    /**
     * assessment : 0
     * createTime : 1505923200000
     * deportId : 0001
     * deportName : 华南仓
     * goodsId : 73328ecb7ff141c0bb045e5da58a0217
     * goodsName : 2017年中吉号纯麻黑古树茶
     * id : 77215cf4e08948e2b60088e491e10fe0
     * imgUrl : static/img/def1.jpg
     * phoneNumber : 13689500606
     * price : 2.32
     * quantity : 100
     * redeem : 0
     * tag : 01
     * tagName : 热门商品
     * total : 232
     * transStatus : 7
     * transStatusName : 已赎回
     * transType : 0004
     * type : 0001
     * typeName : 生茶
     * unit : 01
     * unitName : 克
     * userId : e7e3ee23af1a49528d506c9864d8362b
     * userName : 13689500606
     * zcId : 066ddf75bf57432f8816a4d7f2d0d688
     */

    private String assessment;
    private long createTime;
    private String deportId;
    private String deportName;
    private String goodsId;
    private String goodsName;
    private String id;
    private String imgUrl;
    private String phoneNumber;
    private String price;
    private String quantity;
    private String redeem;
    private String tag;
    private String tagName;
    private String total;
    private String transStatus;
    private String transStatusName;
    private String transType;
    private String type;
    private String typeName;
    private String unit;
    private String unitName;
    private String userId;
    private String userName;
    private String zcId;
    private String expectation;

    public String getExpectation() {
        return expectation;
    }

    public void setExpectation(String expectation) {
        this.expectation = expectation;
    }

    public String getAssessment() {
        return assessment;
    }

    public void setAssessment(String assessment) {
        this.assessment = assessment;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getDeportId() {
        return deportId;
    }

    public void setDeportId(String deportId) {
        this.deportId = deportId;
    }

    public String getDeportName() {
        return StringUtils.isEmpty(deportName) ? "" : deportName + " ";
    }

    public void setDeportName(String deportName) {
        this.deportName = deportName;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRedeem() {
        return redeem;
    }

    public void setRedeem(String redeem) {
        this.redeem = redeem;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus;
    }

    public String getTransStatusName() {
        return transStatusName;
    }

    public void setTransStatusName(String transStatusName) {
        this.transStatusName = transStatusName;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return StringUtils.isEmpty(typeName) ? "" : typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getZcId() {
        return zcId;
    }

    public void setZcId(String zcId) {
        this.zcId = zcId;
    }
}
