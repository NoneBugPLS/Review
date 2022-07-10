package com.zyg.cart.service;

import com.zyg.cart.entity.group.Cart;

import java.util.List;

/**
 * 接口名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
public interface CartService {
    List<Cart> addCart(Long itemId, int num, String name);

    List<Cart> findCartList(String name);
}
