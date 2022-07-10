package com.zyg.user.client;

import com.zyg.common.utils.R;
import com.zyg.user.client.factory.ManagerClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 接口名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
@FeignClient(value="zyg-manager",fallbackFactory = ManagerClientFallbackFactory.class)
//@FeignClient(value = "zyg-manager",fallback = ManagerClientImpl.class)
public interface ManagerClient {

    @GetMapping("manager/brand/findAll")
    R list();
}
