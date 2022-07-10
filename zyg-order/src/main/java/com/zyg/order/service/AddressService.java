package com.zyg.order.service;

import com.zyg.order.entity.TbAddress;

import java.util.List;

/**
 * 接口名：
 * 作者：Lun
 * 功能：
 * 时间：23:34 2022/1/6
 */
public interface AddressService {

    List<TbAddress> findAddressByUserId(String name);
}
