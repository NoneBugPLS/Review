package com.zyg.user.controller;

import com.zyg.common.utils.R;
import com.zyg.user.client.ManagerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
@Controller
public class userController {


    @Autowired
    private ManagerClient managerClient;

    @GetMapping({"/","register.html"})
    public String index(){
        //根据手机号生成验证码

        return "register";
    }



    //查询zyg-manager 品牌
    @GetMapping("user/ManagerBrandList")
    @ResponseBody
    public R list(){
      return managerClient.list();
    }

}
