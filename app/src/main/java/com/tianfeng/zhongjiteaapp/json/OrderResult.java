package com.tianfeng.zhongjiteaapp.json;

import java.util.List;

/**
 * Created by Administrator on 2017/9/21 0021.
 */

public class OrderResult {


    /**
     * code : 0000
     * msg : 查询成功
     * result : {"condition":{},"currentPage":1,"limit":2147483647,"offset":0,"pageSize":10,"result":[{"createTime":1505897053000,"goodsId":"73328ecb7ff141c0bb045e5da58a0217","goodsName":"2017年中吉号纯麻黑古树茶","id":"6548a7e315be4f60a82858ec859628f6","imgUrl":"static/img/def1.jpg","orderStatus":"1","orderStatusName":"审核通过","orderType":"0001","orderTypeName":"暂存","phoneNumber":"13689500606","price":99.88,"quantity":8,"total":799.04,"unit":"03","unitName":"提","updateTime":1505897053000,"userId":"e7e3ee23af1a49528d506c9864d8362b","userName":"13689500606"},{"createTime":1505897714000,"goodsId":"8239f118c7bf4521a7be0d2d937be1e5","goodsName":"2017年中吉号班盆庄园","id":"91a4c9e1c49a43c890f242966547f350","imgUrl":"static/img/def1.jpg","orderStatus":"1","orderStatusName":"审核通过","orderType":"0001","orderTypeName":"暂存","phoneNumber":"13689500606","price":32,"quantity":12,"total":384,"unit":"03","unitName":"提","updateTime":1505897714000,"userId":"e7e3ee23af1a49528d506c9864d8362b","userName":"13689500606"},{"createTime":1505897690000,"goodsId":"1eabe6ec56934cca9dd480058b94de85","goodsName":"2017年中吉号布朗珍品","id":"9d683e5121844ddc847c972c5d650e55","imgUrl":"static/img/def1.jpg","orderStatus":"1","orderStatusName":"审核通过","orderType":"0001","orderTypeName":"暂存","phoneNumber":"13689500606","price":20,"quantity":11,"total":220,"unit":"04","unitName":"件","updateTime":1505897690000,"userId":"e7e3ee23af1a49528d506c9864d8362b","userName":"13689500606"},{"createTime":1505925462000,"goodsId":"73328ecb7ff141c0bb045e5da58a0217","goodsName":"2017年中吉号纯麻黑古树茶","id":"ac271ae452844e15b627314279912a1d","imgUrl":"static/img/def1.jpg","orderStatus":"已暂存","orderType":"0001","orderTypeName":"暂存","phoneNumber":"13689500606","price":30,"quantity":100,"total":3000,"unit":"03","unitName":"提","updateTime":1505925462000,"userId":"e7e3ee23af1a49528d506c9864d8362b","userName":"13689500606"}],"storey":0,"totalPage":1,"totalRows":4}
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
         * currentPage : 1
         * limit : 2147483647
         * offset : 0
         * pageSize : 10
         * result : [{"createTime":1505897053000,"goodsId":"73328ecb7ff141c0bb045e5da58a0217","goodsName":"2017年中吉号纯麻黑古树茶","id":"6548a7e315be4f60a82858ec859628f6","imgUrl":"static/img/def1.jpg","orderStatus":"1","orderStatusName":"审核通过","orderType":"0001","orderTypeName":"暂存","phoneNumber":"13689500606","price":99.88,"quantity":8,"total":799.04,"unit":"03","unitName":"提","updateTime":1505897053000,"userId":"e7e3ee23af1a49528d506c9864d8362b","userName":"13689500606"},{"createTime":1505897714000,"goodsId":"8239f118c7bf4521a7be0d2d937be1e5","goodsName":"2017年中吉号班盆庄园","id":"91a4c9e1c49a43c890f242966547f350","imgUrl":"static/img/def1.jpg","orderStatus":"1","orderStatusName":"审核通过","orderType":"0001","orderTypeName":"暂存","phoneNumber":"13689500606","price":32,"quantity":12,"total":384,"unit":"03","unitName":"提","updateTime":1505897714000,"userId":"e7e3ee23af1a49528d506c9864d8362b","userName":"13689500606"},{"createTime":1505897690000,"goodsId":"1eabe6ec56934cca9dd480058b94de85","goodsName":"2017年中吉号布朗珍品","id":"9d683e5121844ddc847c972c5d650e55","imgUrl":"static/img/def1.jpg","orderStatus":"1","orderStatusName":"审核通过","orderType":"0001","orderTypeName":"暂存","phoneNumber":"13689500606","price":20,"quantity":11,"total":220,"unit":"04","unitName":"件","updateTime":1505897690000,"userId":"e7e3ee23af1a49528d506c9864d8362b","userName":"13689500606"},{"createTime":1505925462000,"goodsId":"73328ecb7ff141c0bb045e5da58a0217","goodsName":"2017年中吉号纯麻黑古树茶","id":"ac271ae452844e15b627314279912a1d","imgUrl":"static/img/def1.jpg","orderStatus":"已暂存","orderType":"0001","orderTypeName":"暂存","phoneNumber":"13689500606","price":30,"quantity":100,"total":3000,"unit":"03","unitName":"提","updateTime":1505925462000,"userId":"e7e3ee23af1a49528d506c9864d8362b","userName":"13689500606"}]
         * storey : 0
         * totalPage : 1
         * totalRows : 4
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
             * createTime : 1505897053000
             * goodsId : 73328ecb7ff141c0bb045e5da58a0217
             * goodsName : 2017年中吉号纯麻黑古树茶
             * id : 6548a7e315be4f60a82858ec859628f6
             * imgUrl : static/img/def1.jpg
             * orderStatus : 1
             * orderStatusName : 审核通过
             * orderType : 0001
             * orderTypeName : 暂存
             * phoneNumber : 13689500606
             * price : 99.88
             * quantity : 8
             * total : 799.04
             * unit : 03
             * unitName : 提
             * updateTime : 1505897053000
             * userId : e7e3ee23af1a49528d506c9864d8362b
             * userName : 13689500606
             */

            private long createTime;
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
            private String total;
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

            public String getTotal() {
                return total;
            }

            public void setTotal(String total) {
                this.total = total;
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
