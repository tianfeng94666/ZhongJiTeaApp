package com.tianfeng.zhongjiteaapp.json;

import java.util.List;

/**
 * Created by 田丰 on 2017/9/17.
 */

public class GetShopsResult {

    /**
     * code : 0000
     * msg : 查询成功
     * result : [{"id":"d7e9125805014e25913eb41eadfcb9e5","shopName":"测试门店"}]
     * jsessionid :
     */

    private String code;
    private String msg;
    private String jsessionid;
    private List<Shop> result;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    public List<Shop> getResult() {
        return result;
    }

    public void setResult(List<Shop> result) {
        this.result = result;
    }

    public static class Shop {
        /**
         * id : d7e9125805014e25913eb41eadfcb9e5
         * shopName : 测试门店
         */

        private String id;
        private String shopName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }
    }
}
