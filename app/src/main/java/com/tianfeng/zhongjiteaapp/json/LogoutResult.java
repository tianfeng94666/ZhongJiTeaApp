package com.tianfeng.zhongjiteaapp.json;

/**
 * Created by 田丰 on 2017/9/19.
 */

public class LogoutResult {

    /**
     * code : 0000
     * msg : 注销成功
     * result : null
     * jsessionid : aa348cb6-4a2c-4c77-9a37-56a0b8003445
     */

    private String code;
    private String msg;
    private Object result;
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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }
}
