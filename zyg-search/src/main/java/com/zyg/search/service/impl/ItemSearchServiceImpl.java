package com.zyg.search.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.zyg.search.entity.ItemEntity;
import com.zyg.search.entity.ItemVo;
import com.zyg.search.service.ItemSearchService;
import org.apache.lucene.queryparser.xml.builders.BooleanQueryBuilder;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：2021/11/14 17:31
 */
@Service
public class ItemSearchServiceImpl implements ItemSearchService {

    @Autowired
    private ElasticsearchRestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;

    //1. 根据查询参数的到结果
    @Override
    public Map<String, Object> search(ItemVo vo) {
        /*-------------------------------- 第一部分：关键字查询 --------------------------------*/
        //1.1 定义返回的Map结果对象
        Map<String,Object> resultMap = new HashMap<>();
        //1.0 定义存放高亮数据的集合
        List<ItemEntity> highlights = new ArrayList<>();
        //1.2 得到查询关键字
        String keywords = vo.getKeywords();
        //1.3 判断
        if (StrUtil.isBlank(keywords)){
            keywords="华为";
        }

        /*-------------------------------- 第二部分：高亮查询与聚合查询 --------------------------------*/
        //1.4 定义查询构建器对象
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        //1.5 多字段查询
        builder.withQuery(QueryBuilders.multiMatchQuery(keywords,"title","category","brand"));  //代表关键字在 "title","category","brand" 三个中查询

        //1.6 进行高亮查询
        builder.withHighlightBuilder(new HighlightBuilder().field("title")
                .preTags("<span style='color:red'>")            //前缀
                .postTags("</span>"));                          //后缀
        //相当于你查询的时候，它会自动搞成：   <span style='color:red'>华为</span>   就是给他个html样式


        //2. 分组查询：添加一个聚合查询
        builder.addAggregation(AggregationBuilders.terms("categoryGroup").field("category.keyword").size(50));


        /*-------------------------------- 第三部分：过滤查询 --------------------------------*/
        //3. 开始过滤查询
        //3.1 组合多个查询条件
        //3.0 定义组合查询的bollquerybuilder对象
        BoolQueryBuilder booleanQuery = QueryBuilders.boolQuery();
        //3.1.1. 进行分类的过滤查询
        if (StrUtil.isNotBlank(vo.getCategory())){
            booleanQuery.filter(QueryBuilders.termQuery("category.keyword",vo.getCategory()));
        }
        //3.1.2. 进行品牌过滤查询
        if (StrUtil.isNotBlank(vo.getBrand())){
            booleanQuery.filter(QueryBuilders.termQuery("brand.keyword",vo.getBrand()));
        }

        //3.1.3 进行规格过滤查询
        if (vo.getSpec() != null && vo.getSpec().size() > 0){
            // 遍历字符串：
            for (String s : vo.getSpec()){
                System.out.println("s = " + s);
                String[] split = s.split(":");
                System.out.println("split = " + split);
                booleanQuery.filter(QueryBuilders.termQuery("specMap." + split[0] + ".keyword",split[1]));
            }

        }

        //3.1.5 价格区间查询：
        if (StrUtil.isNotBlank(vo.getPrice())){
            // 1. 拆分价格  过来的数据 最后一项是 3000-* 要把 * 排除掉
            String price = vo.getPrice();
            String[] split = price.split("-");
            //2. 添加价格区间过滤查询
            if (!split[1].equals("*")){
                booleanQuery.filter(QueryBuilders.rangeQuery("price").gte(split[0]).lte(split[1]));
            }else {
                booleanQuery.filter(QueryBuilders.rangeQuery("price").gte(split[0]));
            }
        }
        //3.1.6 进行价格升降序查询
        if (StrUtil.isNotBlank(vo.getSort())){
            //1. 拆分sort
            String[] split = vo.getSort().split(":");   //split[0]: 排序字段 split[1]：排序的升还是降
            //2. 进行排序：
            builder.withSort(SortBuilders.fieldSort(split[0]).order(split[1].equals("asc") ? SortOrder.ASC : SortOrder.DESC));
        }

        //3.1.4 将布尔查询连接的多个条件与builder进行绑定
        builder.withFilter(booleanQuery);

        //4. 分页：
        builder.withPageable(PageRequest.of(vo.getPage()-1,vo.getPageSize()));


        /*-------------------------------- 第四部分： 分析查询结果 --------------------------------*/

        //1.7 构建查询结果：
        NativeSearchQuery query = builder.build();
        //1.8 分析查询结果
        SearchHits<ItemEntity> searchHits = restTemplate.search(query, ItemEntity.class, IndexCoordinates.of("item"));

        //分页：
        long total = searchHits.getTotalHits();  //得到总记录数
        long totalPage = (long) Math.ceil((double)total/vo.getPageSize());    //计算总页数，ceil向上取整

        for (SearchHit<ItemEntity> searchHit : searchHits) {
            //1.8.1 得到原始数据内容
            ItemEntity content = searchHit.getContent();
            //1.8.2 得到高亮查询标题
            List<String> title = searchHit.getHighlightField("title");
            //1.8.3 把原始的title 变成高亮数据
            if(title != null && title.size() > 0){
                content.setTitle(title.get(0));
                highlights.add(content);
            }
        }
        //2.1 得到聚合查询结果
        ParsedStringTerms parsedStringTerms = searchHits.getAggregations().get("categoryGroup");
        List<? extends Terms.Bucket> buckets = parsedStringTerms.getBuckets();
        //=================两种写法==========================//
        //==========方法一============//
//        List<String> keys = new ArrayList<>();
//        for (Terms.Bucket bucket : buckets) {
//            String keyAsString = bucket.getKeyAsString();
//            keys.add(keyAsString);
//        }
//        System.out.println("keys = " + keys);
        //==========写法二============//
        List<String> categoryList = buckets.stream().map(m -> m.getKeyAsString()).collect(Collectors.toList());
        System.out.println("categoryList = " + categoryList);

        //2.3 得到前面传过来的分类名称
        String category = vo.getCategory();
        if(StrUtil.isBlank(category)){       //说明前台没有提交分类
            if(categoryList != null && categoryList.size() > 0) {
                category = categoryList.get(0);
            }
        }
        System.out.println("category = " + category);
        //根据分类找到模板id，进而找到品牌列表及规格列表
        Map brandAndSpecMap = findBrandAndSpecMap(category);


        /*-------------------------------- 第五部分： 将查询结果放到结果Map --------------------------------*/

        //1.9 将高亮放到resultMap
        resultMap.put("rows",highlights);
        //2.2 将分类放到大集合中
        resultMap.put("categoryList",categoryList);
        //2.5 把品牌一级规格列表放到大集合中
        resultMap.putAll(brandAndSpecMap);

        //4. 添加分页信息
        resultMap.put("total",total);
        resultMap.put("totalPage",totalPage);

        return resultMap;
    }



    /*-------------------------------- 第二部分： --------------------------------*/
    //2.4  根据category查询type进而找到品牌与规格
    private Map findBrandAndSpecMap(String category) {
        //2.1 拿到typeid
        String typeId = (String) redisTemplate.boundHashOps("itemCats").get(category);
        //2.2 通过typeId拿到品牌与规格列表
        String brand = (String) redisTemplate.boundHashOps("brandList").get(typeId);
        List<Map> brandList = JSON.parseArray(brand, Map.class);
        String specs = (String) redisTemplate.boundHashOps("specList").get(typeId);
        List<Map> specList = JSON.parseArray(specs, Map.class);
        //2.3 定义返回 的map
        Map map = new HashMap();
        map.put("brandList",brandList);
        map.put("specList",specList);
        //2.4 返回
        return map;
    }
}
