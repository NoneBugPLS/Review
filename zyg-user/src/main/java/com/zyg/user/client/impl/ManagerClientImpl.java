package com.zyg.user.client.impl;

import com.zyg.common.utils.R;
import com.zyg.user.client.ManagerClient;
import org.springframework.stereotype.Component;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
@Component
public class ManagerClientImpl implements ManagerClient {
    @Override
    public R list() {
        return R.error("外日，Manager服务器挂了");
    }
}
