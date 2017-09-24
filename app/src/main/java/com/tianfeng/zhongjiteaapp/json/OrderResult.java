package com.tianfeng.zhongjiteaapp.json;

import java.util.List;

/**
 * Created by Administrator on 2017/9/21 0021.
 */

public class OrderResult {


    /**
     * code : 0000
     * msg : 查询成功
     * result : {"condition":{},"currentPage":4,"limit":2147483647,"offset":0,"pageSize":10,"result":[{"createTime":1505983777000,"deportName":"华南仓","goodsId":"73328ecb7ff141c0bb045e5da58a0217","goodsName":"2017年中吉号纯麻黑古树茶","id":"fafc4a5eecb244fb8a2ffce7bfaa0780","imgUrl":"static/img/def1.jpg","orderStatus":"3","orderStatusName":"交易成功","orderType":"0004","orderTypeName":"质押","phoneNumber":"13689500606","price":2.32,"quantity":100,"tagName":"热门商品","total":232,"typeName":"生茶","unit":"01","unitName":"克","updateTime":1505983777000,"userId":"e7e3ee23af1a49528d506c9864d8362b","userName":"13689500606"}],"storey":0,"totalPage":4,"totalRows":31}
     */

    private String code;
    private String msg;
    private ResultBeanX result;

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

    public ResultBeanX getResult() {
        return result;
    }

    public void setResult(ResultBeanX result) {
        this.result = result;
    }

    public static class ResultBeanX {
        /**
         * condition : {}
         * currentPage : 4
         * limit : 2147483647
         * offset : 0
         * pageSize : 10
         * result : [{"createTime":1505983777000,"deportName":"华南仓","goodsId":"73328ecb7ff141c0bb045e5da58a0217","goodsName":"2017年中吉号纯麻黑古树茶","id":"fafc4a5eecb244fb8a2ffce7bfaa0780","imgUrl":"static/img/def1.jpg","orderStatus":"3","orderStatusName":"交易成功","orderType":"0004","orderTypeName":"质押","phoneNumber":"13689500606","price":2.32,"quantity":100,"tagName":"热门商品","total":232,"typeName":"生茶","unit":"01","unitName":"克","updateTime":1505983777000,"userId":"e7e3ee23af1a49528d506c9864d8362b","userName":"13689500606"}]
         * storey : 0
         * totalPage : 4
         * totalRows : 31
         */

        private ConditionBean condition;
        private int currentPage;
        private int limit;
        private int offset;
        private int pageSize;
        private int storey;
        private int totalPage;
        private int totalRows;
        private List<ResultBean> result;

        public ConditionBean getCondition() {
            return condition;
        }

        public void setCondition(ConditionBean condition) {
            this.condition = condition;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getStorey() {
            return storey;
        }

        public void setStorey(int storey) {
            this.storey = storey;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public int getTotalRows() {
            return totalRows;
        }

        public void setTotalRows(int totalRows) {
            this.totalRows = totalRows;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class ConditionBean {
        }

        public static class ResultBean {
            /**
             * createTime : 1505983777000
             * deportName : 华南仓
             * goodsId : 73328ecb7ff141c0bb045e5da58a0217
             * goodsName : 2017年中吉号纯麻黑古树茶
             * id : fafc4a5eecb244fb8a2ffce7bfaa0780
             * imgUrl : static/img/def1.jpg
             * orderStatus : 3
             * orderStatusName : 交易成功
             * orderType : 0004
             * orderTypeName : 质押
             * phoneNumber : 13689500606
             * price : 2.32
             * quantity : 100
             * tagName : 热门商品
             * total : 232
             * typeName : 生茶
             * unit : 01
             * unitName : 克
             * updateTime : 1505983777000
             * userId : e7e3ee23af1a49528d506c9864d8362b
             * userName : 13689500606
             */

            private long createTime;
            private String deportName;
            private String goodsId;
            private String goodsName;
            private String id;
            private String imgUrl;
            private String orderStatus;
            private String orderStatusName;
            private String orderType;
            private String orderTypeName;
            private String phoneNumber;
            private String price;
            private String quantity;
            private String tagName;
            private String total;
            private String typeName;
            private String unit;
            private String unitName;
            private long updateTime;
            private String userId;
            private String userName;

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public String getDeportName() {
                return deportName;
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

            public String getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(String orderStatus) {
                this.orderStatus = orderStatus;
            }

            public String getOrderStatusName() {
                return orderStatusName;
            }

            public void setOrderStatusName(String orderStatusName) {
                this.orderStatusName = orderStatusName;
            }

            public String getOrderType() {
                return orderType;
            }

            public void setOrderType(String orderType) {
                this.orderType = orderType;
            }

            public String getOrderTypeName() {
                return orderTypeName;
            }

            public void setOrderTypeName(String orderTypeName) {
                this.orderTypeName = orderTypeName;
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

            public String getTypeName() {
                return typeName;
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

            public long getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(long updateTime) {
                this.updateTime = updateTime;
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
        }
    }
}
