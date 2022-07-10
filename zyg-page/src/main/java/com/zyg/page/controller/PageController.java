package com.zyg.page.controller;

import com.zyg.common.utils.R;
import com.zyg.page.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
@RestController
@RequestMapping
public class PageController {
    @Autowired
    private PageService pageService;

    @GetMapping("/createHtml/{id}")
    public R createHtml(@PathVariable Long id) throws IOException {
        pageService.createHtml(id);
        return R.ok();
    }
}
