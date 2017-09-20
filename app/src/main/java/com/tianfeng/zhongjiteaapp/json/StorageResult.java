package com.tianfeng.zhongjiteaapp.json;

import java.util.List;

/**
 * Created by 田丰 on 2017/9/20.
 */

public class StorageResult {

    /**
     * code : 0000
     * msg : 查询成功
     * result : {"condition":{},"currentPage":0,"limit":2147483647,"offset":0,"pageSize":10,"result":[{"createTime":1505836800000,"deportId":"0001","deportName":"华南仓","endTime":"2017-09-30","goodsId":"73328ecb7ff141c0bb045e5da58a0217","goodsName":"2017年中吉号纯麻黑古树茶","id":"066ddf75bf57432f8816a4d7f2d0d688","imgUrl":"static/img/def1.jpg","phoneNumber":"13689500606","price":99.88,"quantity":8,"shzt":"0","shztName":"未赎回","tag":"01","tagName":"热门商品","total":799.04,"transType":"0001","type":"0001","typeName":"生茶","unit":"03","unitName":"提","updateTime":1505836800000,"userId":"e7e3ee23af1a49528d506c9864d8362b","userName":"13689500606","zyzt":"0","zyztName":"未质押"},{"createTime":1505836800000,"deportId":"0002","deportName":"云南仓","endTime":"2017-09-30","goodsId":"8239f118c7bf4521a7be0d2d937be1e5","goodsName":"2017年中吉号班盆庄园","id":"3508281e486e4f4cb1dcc8171c0c53a7","imgUrl":"static/img/def1.jpg","phoneNumber":"13689500606","price":32,"quantity":12,"shzt":"0","shztName":"未赎回","tag":"03","tagName":"普通茶","total":384,"transType":"0001","type":"0001","typeName":"生茶","unit":"03","unitName":"提","updateTime":1505836800000,"userId":"e7e3ee23af1a49528d506c9864d8362b","userName":"13689500606","zyzt":"0","zyztName":"未质押"},{"createTime":1505836800000,"deportId":"0001","deportName":"华南仓","endTime":"2017-09-28","goodsId":"1eabe6ec56934cca9dd480058b94de85","goodsName":"2017年中吉号布朗珍品","id":"bc3c9da4025c4c3f9ada306e0cc51bb0","imgUrl":"static/img/def1.jpg","phoneNumber":"13689500606","price":20,"quantity":11,"shzt":"0","shztName":"未赎回","tag":"03","tagName":"普通茶","total":220,"transType":"0001","type":"0001","typeName":"生茶","unit":"04","unitName":"件","updateTime":1505836800000,"userId":"e7e3ee23af1a49528d506c9864d8362b","userName":"13689500606","zyzt":"0","zyztName":"未质押"}],"storey":0,"totalPage":1,"totalRows":3}
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
         * currentPage : 0
         * limit : 2147483647
         * offset : 0
         * pageSize : 10
         * result : [{"createTime":1505836800000,"deportId":"0001","deportName":"华南仓","endTime":"2017-09-30","goodsId":"73328ecb7ff141c0bb045e5da58a0217","goodsName":"2017年中吉号纯麻黑古树茶","id":"066ddf75bf57432f8816a4d7f2d0d688","imgUrl":"static/img/def1.jpg","phoneNumber":"13689500606","price":99.88,"quantity":8,"shzt":"0","shztName":"未赎回","tag":"01","tagName":"热门商品","total":799.04,"transType":"0001","type":"0001","typeName":"生茶","unit":"03","unitName":"提","updateTime":1505836800000,"userId":"e7e3ee23af1a49528d506c9864d8362b","userName":"13689500606","zyzt":"0","zyztName":"未质押"},{"createTime":1505836800000,"deportId":"0002","deportName":"云南仓","endTime":"2017-09-30","goodsId":"8239f118c7bf4521a7be0d2d937be1e5","goodsName":"2017年中吉号班盆庄园","id":"3508281e486e4f4cb1dcc8171c0c53a7","imgUrl":"static/img/def1.jpg","phoneNumber":"13689500606","price":32,"quantity":12,"shzt":"0","shztName":"未赎回","tag":"03","tagName":"普通茶","total":384,"transType":"0001","type":"0001","typeName":"生茶","unit":"03","unitName":"提","updateTime":1505836800000,"userId":"e7e3ee23af1a49528d506c9864d8362b","userName":"13689500606","zyzt":"0","zyztName":"未质押"},{"createTime":1505836800000,"deportId":"0001","deportName":"华南仓","endTime":"2017-09-28","goodsId":"1eabe6ec56934cca9dd480058b94de85","goodsName":"2017年中吉号布朗珍品","id":"bc3c9da4025c4c3f9ada306e0cc51bb0","imgUrl":"static/img/def1.jpg","phoneNumber":"13689500606","price":20,"quantity":11,"shzt":"0","shztName":"未赎回","tag":"03","tagName":"普通茶","total":220,"transType":"0001","type":"0001","typeName":"生茶","unit":"04","unitName":"件","updateTime":1505836800000,"userId":"e7e3ee23af1a49528d506c9864d8362b","userName":"13689500606","zyzt":"0","zyztName":"未质押"}]
         * storey : 0
         * totalPage : 1
         * totalRows : 3
         */

        private ConditionBean condition;
        private int currentPage;
        private int limit;
        private int offset;
        private int pageSize;
        private int storey;
        private int totalPage;
        private int totalRows;
        private List<OrderBean> result;

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

        public List<OrderBean> getResult() {
            return result;
        }

        public void setResult(List<OrderBean> result) {
            this.result = result;
        }

        public static class ConditionBean {
        }


    }
}
