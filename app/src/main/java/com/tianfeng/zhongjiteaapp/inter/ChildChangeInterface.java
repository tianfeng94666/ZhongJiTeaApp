package com.tianfeng.zhongjiteaapp.inter;

import com.tianfeng.zhongjiteaapp.json.GetAearResult;

import java.util.List;

/**
 * Created by Administrator on 2017/10/10 0010.
 */

public interface ChildChangeInterface {
    public void change(List<GetAearResult.ResultBean.ChildrenBean> childrenBeanList);
}
