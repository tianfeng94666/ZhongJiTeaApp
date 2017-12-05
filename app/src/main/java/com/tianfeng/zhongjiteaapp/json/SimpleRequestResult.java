package com.tianfeng.zhongjiteaapp.json;

/**
 * Created by Administrator on 2017/12/5 0005.
 */

public class SimpleRequestResult {

    /**
     * code : 0000
     * msg : 已提交暂存申请,待审核
     * result : 已提交暂存申请,待审核
     * jsessionid : dd475049-1c89-4790-87ee-7e3ef9130d76
     */

    private String code;
    private String msg;
    private String result;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }
}
