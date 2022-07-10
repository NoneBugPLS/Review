package com.zyg.manager.controller;

import com.zyg.common.utils.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
@RestController
@RequestMapping
@RefreshScope
public class Hello {
    @Value("${user.name}")
    private String name;
    @Value("${user.age}")
    private int age;

    @GetMapping("/hello")
    public R hello(){
        return R.ok().put("name",name).put("age",age);
    }
}
