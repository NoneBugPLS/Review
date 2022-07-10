package com.zyg.page.service.impl;

import com.zyg.page.entity.GoodsDescEntity;
import com.zyg.page.entity.GoodsEntity;
import com.zyg.page.service.GoodsDescService;
import com.zyg.page.service.GoodsService;
import com.zyg.page.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
@Service
public class PageServiceImpl implements PageService {
    @Autowired
    private TemplateEngine engine;    //引入thymeleaf模板
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsDescService goodsDescService;



    //1. 根据id生成html页面
    @Override
    public void createHtml(Long id) throws IOException {           //id：代表spu商品id
        //1.1  定义模板工作的上下文对象             自动生成的是IContext context;   IContext是接口  Context 就是它的实现类
        Context context = new Context();
        //1.2 定义上下文环境关联的数据集合
        Map<String, Object> dataMap = new HashMap<>();
        //这时候这里面没有值  把值穿进来
        //1.4 拿到商品id
        GoodsEntity goodsEntity = goodsService.getById(id);
//        System.out.println("=========================================");
//        System.out.println("goodsEntity = " + goodsEntity);
        //1.6 拿到商品描述信息
        GoodsDescEntity goodsDescEntity = goodsDescService.getById(id);

        //1.5 将商品放到dataMap中
        dataMap.put("goods",goodsEntity);
        //1.7 把decc放到map中
        dataMap.put("goodsDesc",goodsDescEntity);


        //1.6 构造输出流对象，指定输出的静态页面的位置
        Writer writer = new FileWriter("D:\\nginx-1.8.0\\html\\item\\" + id + ".html");



        //1.3 绑定集合到上下文对象
        context.setVariables(dataMap);
        //参数1：代表模板的视图名 参数2：模板工作的上下文环境，参数3：指定输出的静态页面的输出流
        engine.process("item",context,writer);
    }
}
