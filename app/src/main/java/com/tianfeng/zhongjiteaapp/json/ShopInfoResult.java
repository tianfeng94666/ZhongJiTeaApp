package com.tianfeng.zhongjiteaapp.json;

/**
 * Created by Administrator on 2017/9/19 0019.
 */

public class ShopInfoResult {

    /**
     * code : 0000
     * msg : 查询成功
     * result : {"id":"d7e9125805014e25913eb41eadfcb9e5","shopName":"测试门店","area":null,"address":"武汉市洪山区","tel":"13163285056","busiStartTime":"08:30:00","busiEndTime":"23:30:00"}
     * jsessionid :
     */

    private String code;
    private String msg;
    private ResultBean result;
    private String jsessionid;

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

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    public static class ResultBean {
        /**
         * id : d7e9125805014e25913eb41eadfcb9e5
         * shopName : 测试门店
         * area : null
         * address : 武汉市洪山区
         * tel : 13163285056
         * busiStartTime : 08:30:00
         * busiEndTime : 23:30:00
         */

        private String id;
        private String shopName;
        private Object area;
        private String address;
        private String tel;
        private String busiStartTime;
        private String busiEndTime;

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

        public Object getArea() {
            return area;
        }

        public void setArea(Object area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public String getBusiStartTime() {
            return busiStartTime;
        }

        public void setBusiStartTime(String busiStartTime) {
            this.busiStartTime = busiStartTime;
        }

        public String getBusiEndTime() {
            return busiEndTime;
        }

        public void setBusiEndTime(String busiEndTime) {
            this.busiEndTime = busiEndTime;
        }
    }
}
