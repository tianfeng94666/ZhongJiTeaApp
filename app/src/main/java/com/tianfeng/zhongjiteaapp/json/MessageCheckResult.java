package com.tianfeng.zhongjiteaapp.json;

/**
 * Created by 田丰 on 2017/9/17.
 */

public class MessageCheckResult {

    /**
     * code : 0000
     * msg : 验证成功
     * result : null
     * jsessionid : 726eaf76-4621-488c-81f7-269161f4f389
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
