package com.tianfeng.zhongjiteaapp.json;

import java.util.List;

/**
 * Created by 田丰 on 2017/9/17.
 */

public class GetProductResult {
    /**
     * code : 0000
     * msg : 查询成功
     * result : {"offset":0,"limit":2147483647,"result":[{"unitName":"提","deportId":"0002","typeName":"生茶","type":"0001","tagName":"新茶上市","informationUrl":"api/goods/content/4c00db720d614e1aad9270fa5581764c","imgUrl":"static/img/def1.jpg","unit":"03","price":10,"deportName":"云南仓","id":"4c00db720d614e1aad9270fa5581764c","tag":"02","goodsName":"测试商品","introduction":"【品名】班盆庄园；\n【年份】2017年；\n【生熟】生茶（老班盆古树茶，云车间制茶）；\n【制作工艺】手工石磨饼；\n【规格】200克/饼，7饼/提，28饼/件；"},{"unitName":"克","deportId":"0002","typeName":"生茶","type":"0001","tagName":"热门商品","informationUrl":"api/goods/content/e2a7ab05d989459ab58a3e338ffa9ac0","imgUrl":"static/img/def1.jpg","unit":"01","createTime":1505574541000,"price":232,"deportName":"云南仓","id":"e2a7ab05d989459ab58a3e338ffa9ac0","tag":"01","goodsName":"werwe","introduction":"32"},{"unitName":"饼","deportId":"0002","typeName":"熟茶","type":"0002","tagName":"热门商品","informationUrl":"api/goods/content/083727d1440a41ca9d94b93c801b06a0","imgUrl":"static/img/def1.jpg","unit":"02","createTime":1505574754000,"price":123213,"deportName":"云南仓","id":"083727d1440a41ca9d94b93c801b06a0","tag":"01","goodsName":"qweqwe","introduction":"213"}],"pageSize":10,"currentPage":0,"totalRows":3,"totalPage":1,"condition":{},"storey":0}
     * jsessionid : 094d72f8-c05a-4a1b-a13f-48ec329559ea
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
         * offset : 0
         * limit : 2147483647
         * result : [{"unitName":"提","deportId":"0002","typeName":"生茶","type":"0001","tagName":"新茶上市","informationUrl":"api/goods/content/4c00db720d614e1aad9270fa5581764c","imgUrl":"static/img/def1.jpg","unit":"03","price":10,"deportName":"云南仓","id":"4c00db720d614e1aad9270fa5581764c","tag":"02","goodsName":"测试商品","introduction":"【品名】班盆庄园；\n【年份】2017年；\n【生熟】生茶（老班盆古树茶，云车间制茶）；\n【制作工艺】手工石磨饼；\n【规格】200克/饼，7饼/提，28饼/件；"},{"unitName":"克","deportId":"0002","typeName":"生茶","type":"0001","tagName":"热门商品","informationUrl":"api/goods/content/e2a7ab05d989459ab58a3e338ffa9ac0","imgUrl":"static/img/def1.jpg","unit":"01","createTime":1505574541000,"price":232,"deportName":"云南仓","id":"e2a7ab05d989459ab58a3e338ffa9ac0","tag":"01","goodsName":"werwe","introduction":"32"},{"unitName":"饼","deportId":"0002","typeName":"熟茶","type":"0002","tagName":"热门商品","informationUrl":"api/goods/content/083727d1440a41ca9d94b93c801b06a0","imgUrl":"static/img/def1.jpg","unit":"02","createTime":1505574754000,"price":123213,"deportName":"云南仓","id":"083727d1440a41ca9d94b93c801b06a0","tag":"01","goodsName":"qweqwe","introduction":"213"}]
         * pageSize : 10
         * currentPage : 0
         * totalRows : 3
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
        private List<Product> result;

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

        public List<Product> getResult() {
            return result;
        }

        public void setResult(List<Product> result) {
            this.result = result;
        }

        public static class ConditionBean {
        }

    }
}
