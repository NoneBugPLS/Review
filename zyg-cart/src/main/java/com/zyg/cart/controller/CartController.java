package com.zyg.cart.controller;

import com.alipay.api.domain.Car;
import com.zyg.cart.entity.group.Cart;
import com.zyg.cart.service.CartService;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
@Controller
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private HttpServletRequest request;

    /**
     * 作者：Lun
     * 功能：1. 欢迎页面
     * 时间：2021/12/30 22:38
     * 参数：
     * 返回值：
     */

    @GetMapping({"/","/cart.html"})
    public String index(Model model){
        //1.1 获取用户名（cas登录名的获取方式）
        AttributePrincipal principal = (AttributePrincipal)request.getUserPrincipal();
        String name = principal.getName();
        //1.2 根据登录名，得到购物车列表
        List<Cart> cartList = cartService.findCartList(name);
        //1.3 放到mode中
        model.addAttribute("cartList",cartList);
        return "cart";
    }

    /**
     * 作者：Lun
     * 功能：2. 添加商品到购物车
     * 时间：2021/12/30 23:18
     * 参数：
     * 返回值：
     */
    @GetMapping("/addCart")
    public String addCart(Long itemId,int num,Model model){

        //2.0 获取用户名（cas登录名的获取方式）
        AttributePrincipal principal = (AttributePrincipal)request.getUserPrincipal();
        String name = principal.getName();

        //2.1 添加商品到购物车列表Cart
        List<Cart> cartList = cartService.addCart(itemId,num,name);
        //2.2 将商品放到model中
        model.addAttribute("cartList",cartList);
        return "cart";
    }
}
