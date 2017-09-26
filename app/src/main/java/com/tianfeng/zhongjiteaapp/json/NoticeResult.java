package com.tianfeng.zhongjiteaapp.json;

import java.util.List;

/**
 * Created by Administrator on 2017/9/18 0018.
 */

public class NoticeResult{
    /**
     * code : 0000
     * msg : 查询成功
     * result : {"offset":0,"limit":2147483647,"result":[{"id":"dfcb7663180f45db84063e985b2b4507","title":"的路劲够多 ","content":"<p>李开复大圣归来弄得那个地&nbsp;<\/p>","createTime":1505714733000}],"pageSize":3,"currentPage":1,"totalRows":1,"totalPage":1,"condition":{},"storey":0}
     * jsessionid : eb5ea883-0b25-4b50-b023-8f28f9d0308f
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
         * result : [{"id":"dfcb7663180f45db84063e985b2b4507","title":"的路劲够多 ","content":"<p>李开复大圣归来弄得那个地&nbsp;<\/p>","createTime":1505714733000}]
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
        private List<ResultBean> result;

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
             * id : dfcb7663180f45db84063e985b2b4507
             * title : 的路劲够多
             * content : <p>李开复大圣归来弄得那个地&nbsp;</p>
             * createTime : 1505714733000
             */

            private String id;
            private String title;
            private String content;
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

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }
        }
    }
}
