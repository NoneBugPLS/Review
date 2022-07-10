package com.zyg.order.controller;

import com.zyg.order.entity.Cart;
import com.zyg.order.entity.TbAddress;
import com.zyg.order.service.AddressService;
import com.zyg.order.service.OrderService;
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
 * 时间：23:15 2022/1/6
 */
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private AddressService addressService;



    /**
     * 作者：Lun
     * 功能：默认页面
     * 时间：2022/1/6 23:15
     * 参数：
     * 返回值：
     */

    @GetMapping({"/","getOrderInfo"})
    public String getOrderInfo(Model model){
        //第一部分：显示登录用户的地址列表
        //1.1 获取用户名（cas登录名的获取方式）
        String name = getUsername();
        //1.2 根据用户名查询地址列表
        List<TbAddress> addressList = addressService.findAddressByUserId(name);
        //1.3 将地址列表放到mode中
        model.addAttribute("addressList",addressList);
        System.out.println("addressList = " + addressList);


        return "getOrderInfo";
    }


    //CAS获取用户名：
    private String getUsername() {
        AttributePrincipal principal = (AttributePrincipal)request.getUserPrincipal();
        return principal.getName();
    }
}
