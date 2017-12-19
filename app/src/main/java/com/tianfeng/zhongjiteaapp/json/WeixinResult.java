package com.tianfeng.zhongjiteaapp.json;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public class WeixinResult {

    /**
     * access_token : 5_klTRE_1MhsV9sN90kfGZGUs4oc4OA9rKjAu4i0qUBoWFPNmVV1L3eYeUUrIYrI_G1UocoDtzekGv_JoETlxppJRU1RanN_ODBIyTUwSh-K8
     * expires_in : 7200
     * refresh_token : 5_zrYVl9h1H6cBuZULdcpgW1dUVoSAGSR88ifdWuwoI5tolkCjdFSA2PakaLm_s34DXDjPlT9huxzHIeUm3YLVQtznzZl3DmN5FEcmzG6GeaQ
     * openid : orWgd1rXxU8SmOGrwjowti4OiL7I
     * scope : snsapi_userinfo
     * unionid : oCwd_01mHku-c7qxKtEhCNstqnbE
     */

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
