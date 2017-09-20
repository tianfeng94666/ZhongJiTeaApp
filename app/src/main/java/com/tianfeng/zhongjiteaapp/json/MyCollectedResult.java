package com.tianfeng.zhongjiteaapp.json;

import java.util.List;

/**
 * Created by Administrator on 2017/9/20 0020.
 */

public class MyCollectedResult {

    /**
     * code : 0000
     * msg : 查询成功
     * result : [{"unitName":"克","deportId":"0001","typeName":"熟茶","type":"0002","tagName":"新茶上市","informationUrl":"api/goods/content/eb320e4e307145c382f01ff46510b5ba","imgUrl":"/file/crop/image/goods/2017/img_1505887650697.png","unit":"01","createTime":1505803017000,"price":100,"deportName":"华南仓","id":"eb320e4e307145c382f01ff46510b5ba","tag":"02","goodsName":"古风茶","introduction":"产自华南"},{"unitName":"克","deportId":"0002","typeName":"生茶","type":"0001","tagName":"新茶上市","informationUrl":"api/goods/content/f39fa024048f48a28a9096b12de7339b","imgUrl":"/file/crop/image/goods/2017/img_1505887683587.png","unit":"01","createTime":1505874110000,"price":100,"deportName":"云南仓","id":"f39fa024048f48a28a9096b12de7339b","tag":"02","goodsName":"一品红.红润","introduction":"一品红"},{"unitName":"克","deportId":"0001","typeName":"生茶","type":"0001","tagName":"新茶上市","informationUrl":"api/goods/content/d051bace7d05489c963e3faabe0dfdf9","imgUrl":"/file/crop/image/goods/2017/img_1505887618128.png","unit":"01","createTime":1505874119000,"price":100,"deportName":"华南仓","id":"d051bace7d05489c963e3faabe0dfdf9","tag":"02","goodsName":"一品红","introduction":"一品红"},{"unitName":"克","deportId":"0002","typeName":"生茶","type":"0001","tagName":"新茶上市","informationUrl":"api/goods/content/6f21c3337ced4fbd8d70d5d8bb346423","imgUrl":"/file/crop/image/goods/2017/img_1505887483999.png","unit":"01","createTime":1505876954000,"price":12,"deportName":"云南仓","id":"6f21c3337ced4fbd8d70d5d8bb346423","tag":"02","goodsName":"古御风","introduction":"古御风"}]
     * jsessionid : 2b93a09d-e8ff-4a24-a3a4-405a041aa667
     */

    private String code;
    private String msg;
    private String jsessionid;
    private List<Product> result;

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

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    public List<Product> getResult() {
        return result;
    }

    public void setResult(List<Product> result) {
        this.result = result;
    }

}
