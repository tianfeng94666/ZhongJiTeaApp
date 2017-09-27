package com.tianfeng.zhongjiteaapp.json;

import java.util.List;

/**
 * Created by Administrator on 2017/9/18 0018.
 */

public class AdResult {

    /**
     * code : 0000
     * msg : 查询成功
     * result : {"offset":0,"limit":2147483647,"result":[{"id":"bdebbaeaedee4c379e37f74cceb7b56a","title":"测试","imgUrl":"/file/crop/image/goods/2017/img_1505315498535.bmp","url":"https://www.baidu.com/","type":null,"createTime":1505488456000}],"pageSize":3,"currentPage":1,"totalRows":1,"totalPage":1,"condition":{},"storey":0}
     * jsessionid : 09c1fef5-7188-4c31-a876-d19d48764305
     */

    private String code;
    private String msg;
    private ResultBeanX result;
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

    public ResultBeanX getResult() {
        return result;
    }

    public void setResult(ResultBeanX result) {
        this.result = result;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    public static class ResultBeanX {
        /**
         * offset : 0
         * limit : 2147483647
         * result : [{"id":"bdebbaeaedee4c379e37f74cceb7b56a","title":"测试","imgUrl":"/file/crop/image/goods/2017/img_1505315498535.bmp","url":"https://www.baidu.com/","type":null,"createTime":1505488456000}]
         * pageSize : 3
         * currentPage : 1
         * totalRows : 1
         * totalPage : 1
         * condition : {}
         * storey : 0
         */

        private int offset;
        private int limit;
        private int pageSize;
        private int currentPage;
        private int totalRows;
        private int totalPage;
        private ConditionBean condition;
        private int storey;
        private List<AdBean> result;

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public int getTotalRows() {
            return totalRows;
        }

        public void setTotalRows(int totalRows) {
            this.totalRows = totalRows;
        }

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        public ConditionBean getCondition() {
            return condition;
        }

        public void setCondition(ConditionBean condition) {
            this.condition = condition;
        }

        public int getStorey() {
            return storey;
        }

        public void setStorey(int storey) {
            this.storey = storey;
        }

        public List<AdBean> getResult() {
            return result;
        }

        public void setResult(List<AdBean> result) {
            this.result = result;
        }

        public static class ConditionBean {
        }


    }
}
