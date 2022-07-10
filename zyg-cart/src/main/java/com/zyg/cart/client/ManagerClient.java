package com.zyg.cart.client;

import com.zyg.cart.entity.ItemEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 接口名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
@FeignClient("zyg-manager")
public interface ManagerClient {


    //1. 根据item id 拿到商品信息
    @GetMapping("manager/item/findById/{id}")
    ItemEntity findById(@PathVariable("id") String id);
}
