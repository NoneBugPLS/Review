package com.zyg.user.client.factory;

import com.zyg.common.utils.R;
import com.zyg.user.client.ManagerClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
@Component
public class ManagerClientFallbackFactory implements FallbackFactory<ManagerClient>{
    @Override
    public ManagerClient create(Throwable throwable) {
        return () -> R.error("zyg-manager服务挂了【fallbackFactory】。。。");
    }
}
