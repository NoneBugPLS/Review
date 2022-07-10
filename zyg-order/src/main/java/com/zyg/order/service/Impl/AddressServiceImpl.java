package com.zyg.order.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zyg.order.dao.TbAddressMapper;
import com.zyg.order.entity.TbAddress;
import com.zyg.order.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：23:36 2022/1/6
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private TbAddressMapper addressMapper;

    //1. 根据登录名查询地址列表
    @Override
    public List<TbAddress> findAddressByUserId(String name) {
        return addressMapper.selectList(new QueryWrapper<TbAddress>().eq("user_id",name));
    }
}
