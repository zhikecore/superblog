package com.zhike.blogwebapi.controller;

//import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
//import cn.afterturn.easypoi.excel.entity.ExportParams;
//import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
//import cn.afterturn.easypoi.excel.export.ExcelExportService;
//import cn.afterturn.easypoi.word.parse.excel.ExcelEntityParse;
import com.zhike.blogbase.annotation.EnableResponseResult;
import com.zhike.blogbase.annotation.IgnoreResponseResult;
import com.zhike.blogbase.result.PagedResult;
//import com.zhike.blogbase.utils.WorkBookUtils;
import com.zhike.blogmanager.cache.ArticleCacheManager;
import com.zhike.blogpojo.AO.ArticleAO;
import com.zhike.blogpojo.DO.Adminuser;
import com.zhike.blogpojo.DO.Article;
import com.zhike.blogpojo.DTO.ArticleDto;
import com.zhike.blogpojo.VO.ArticleVO;
import com.zhike.blogpojo.VO.CacheArticleVO;
import com.zhike.blogservice.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Workbook;
import org.apache.catalina.User;
//import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Copyright (C) 2022  智客工坊(52interview.com)
 * The SpringBoot Super-blog Project.
 * All rights reserved.
 * <p>
 * > Github地址: https://github.com/zhikecore/superblog.git
 * > 教程地址: https://www.52interview.com/book/36
 * > 智客工坊社区：https://www.52interview.com/
 * <p>
 * 智客工坊(52interview.com) - 经验创造价值,分享成就未来。
 * <p>
 * ArticleController at 2022/1/16 21:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */

@Api(tags = "文章操作")
@RestController
@RequestMapping("/api/v1/articles")
@AllArgsConstructor
@EnableResponseResult
@IgnoreResponseResult
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Resource
    private ArticleCacheManager articleCacheManager;

    /**
     * 分页获取文章列表
     * @param ArticleDto
     * @return
     */
    @ApiOperation(value = "分页获取文章列表", notes = "分页获取文章列表")
    @GetMapping
    public PagedResult<ArticleVO> getArtiles(@Valid ArticleDto ArticleDto) {
        // 封装值到AO
        ArticleAO articleAO = new ArticleAO();
        BeanUtils.copyProperties(ArticleDto, articleAO);

        //List<Article> carousels=articleService.getCarousels();
        //PagedResult<ArticleVO>  results=articleService.findList(articleAO);

        return articleService.findList(articleAO);
    }

//    @ApiOperation(value = "获取缓存文章列表", notes = "获取缓存文章列表")
//    @GetMapping("/cache")
//    public  List<Article> getCacheArticles()
//    {
//        List<Article> cacheArticles=articleCacheManager.getCacheArticles();
//        return  cacheArticles;
//    }

    @ApiOperation(value = "获取缓存文章列表", notes = "获取缓存文章列表")
    @GetMapping("/cache")
    public  List<CacheArticleVO> getCacheArticles()
    {
        List<CacheArticleVO> cacheArticles=articleCacheManager.getCacheArticlesV2();
        return  cacheArticles;
    }

//    @ApiOperation(value = "导出到Excel", notes = "导出到Excel")
//    @PostMapping
//    public  String  exportToExcel()
//    {
//        Map<String, List<Adminuser>> dic =new HashMap<String, List<Adminuser>>();
//
//        List<Adminuser> userOnes=new ArrayList<Adminuser>();
//
//        Adminuser userOne=new Adminuser();
//        userOne.setId(1);
//        userOne.setAccount("张三");
//        userOne.setNickName("zhangsan");
//        userOnes.add(userOne);
//        dic.put("class1",userOnes);
//
//        List<Adminuser> userTwos=new ArrayList<Adminuser>();
//        Adminuser userTwo=new Adminuser();
//        userTwo.setId(1);
//        userTwo.setAccount("李四");
//        userTwo.setNickName("lisa");
//        userTwos.add(userOne);
//        dic.put("class2",userTwos);
//
//
////        List<Map<String, Object>> lists = new ArrayList<>();
////        dic.forEach((k, v) -> {
////            Map<String, Object> temp1 = WorkBookUtils.createOneSheet(k,  "sheet_"+k, Adminuser.class, v);
////            lists.add(temp1);
////        });
////        Workbook workbook = WorkBookUtils.mutiSheet(lists);
////        FileOutputStream fos = new FileOutputStream(saveFile);
////        workbook.write(fos);
////        fos.close();
//
//        return  "";
//    }



}
