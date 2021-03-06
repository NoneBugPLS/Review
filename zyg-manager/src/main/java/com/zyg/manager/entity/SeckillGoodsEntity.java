package com.zyg.manager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author LunTian
 * @email 841674975@qq.com
 * @date 2021-11-23 23:02:00
 */
@Data
@TableName("tb_seckill_goods")
public class SeckillGoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
	/**
	 * spu ID
	 */
	private String goodsId;
	/**
	 * sku ID
	 */
	private String itemId;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 商品图片
	 */
	private String smallPic;
	/**
	 * 原价格
	 */
	private BigDecimal price;
	/**
	 * 秒杀价格
	 */
	private BigDecimal costPrice;
	/**
	 * 商家ID
	 */
	private String sellerId;
	/**
	 * 添加日期
	 */
	private Date createTime;
	/**
	 * 审核日期
	 */
	private Date checkTime;
	/**
	 * 审核状态
	 */
	private String status;
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 秒杀商品数
	 */
	private Integer num;
	/**
	 * 剩余库存数
	 */
	private Integer stockCount;
	/**
	 * 描述
	 */
	private String introduction;

}
