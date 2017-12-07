package com.tianfeng.zhongjiteaapp.json;

/**
 * Created by Administrator on 2017/12/7 0007.
 */

public class StorageCountResult {

    /**
     * code : 0000
     * msg : 查询成功
     * result : 2
     * jsessionid : null
     */

    private String code;
    private String msg;
    private int result;
    private Object jsessionid;

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

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Object getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(Object jsessionid) {
        this.jsessionid = jsessionid;
    }
}
