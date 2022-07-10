package com.zyg.manager.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zyg.common.utils.PageUtils;
import com.zyg.manager.entity.TypeTemplateEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-23 23:02:00
 */
public interface TypeTemplateService extends IService<TypeTemplateEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<TypeTemplateEntity> findAll();
}

