package com.zhike.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.export.ExcelExportService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhike.blogbase.annotation.EnableAuth;
import com.zhike.blogbase.annotation.IgnoreAuth;
import com.zhike.blogbase.annotation.RecordLog;
import com.zhike.blogbase.result.PagedResult;
//import com.zhike.blogbase.utils.WorkBookUtils;
import com.zhike.blogbase.utils.ExcelUtiles;
import com.zhike.blogbase.utils.JavaBean2Map;
import com.zhike.blogbase.utils.WorkBookUtils;
import com.zhike.blogpojo.AO.ArticleAO;
import com.zhike.blogpojo.DO.Adminuser;
import com.zhike.blogpojo.DO.Article;
import com.zhike.blogpojo.VO.ArticleVO;
import com.zhike.blogpojo.VO.CacheArticleVO;
import com.zhike.blogpojo.VO.CarouselVO;
import com.zhike.blogpojo.VO.UserVO;
import com.zhike.blogservice.AdminUserService;
import com.zhike.blogservice.ArticleService;
import com.zhike.blogservice.CarouselService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
//import org.omg.CORBA.FieldNameHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

//import  com.zhike.blogservice.ArticleService;

/**
 * Copyright (C) 2022  ????????????(52interview.com)
 * The SpringBoot Super-blog Project.
 * All rights reserved.
 * <p>
 * > Github??????: https://github.com/zhikecore/superblog.git
 * > ????????????: https://www.52interview.com/book/36
 * > ?????????????????????https://www.52interview.com/
 * <p>
 * ????????????(52interview.com) - ??????????????????,?????????????????????
 * <p>
 * DefaultController at 2022/01/16 18:25,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Controller
//@RequestMapping("/default")
@RequestMapping("/")
@Slf4j
public class DefaultController {

    @Resource
    private ArticleService articleService;

    @Resource
    private AdminUserService adminUserService;

    @Resource
    private CarouselService carouselService;

//    @RecordLog(funModule="default",funMethod="index")
    //@RequestMapping("/index")
@RequestMapping("/")
    public ModelAndView Index(
            HttpServletRequest request,
            Article article,
            @RequestParam(value = "pageNum",defaultValue = "0") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {

        log.debug("debug:default index...");
        log.info("info: access to default index ?????????!");
        log.error("error:default index...");

        System.out.println("before:"+article.getTitle());
        String title = request.getParameter("title");
        System.out.println("after:"+title);

        //????????????AO

        ArticleAO ao = new ArticleAO();
        ao.setLimit(pageSize);
        ao.setPage(pageNum);

        //?????????????????????????????????index.html  ???????????????????????????,??????????????????
        //ModelAndView modelAndView = new ModelAndView("default/index");
        ModelAndView modelAndView = new ModelAndView("/index");
        IPage<ArticleVO> page=articleService.searchByPage(pageNum,pageSize,article.getTitle());

        //?????????setAttriute("pageInfo",pageInfo)
        modelAndView.addObject("page",page);
        modelAndView.addObject("article",article);

        List<CarouselVO> carouselVOS=carouselService.getCarousels();
        modelAndView.addObject("carousels",carouselVOS);

        return modelAndView;

    }

    /*@RequestMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        //List<SysUser> list = sysUserService.all(user).getData();
        ArticleAO ao=new ArticleAO();
        ao.setPage(1);
        ao.setLimit(10);

        PagedResult<ArticleVO> pagedResult= articleService.findList(ao);

        //List<Adminuser> users=adminUserService.list();
        //??????????????????
        Map<String, List<ArticleVO>> dic = pagedResult.getItems().stream().collect(Collectors.groupingBy(ArticleVO::getArticleTypeName));

        List<Map<String, Object>> lists = new ArrayList<>();
        dic.forEach((k, v) -> {
            Map<String, Object> temp1 = WorkBookUtils.createOneSheet("sheet_"+k, k, ArticleVO.class, v);
            lists.add(temp1);
        });
        Workbook workbook = WorkBookUtils.mutiSheet(lists);
        //Workbook workbook= ExcelExportUtil.exportExcel(new ExportParams("??????????????????","????????????"),Adminuser.class,users);

        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode("????????????.xls","UTF-8"));
        ServletOutputStream outputStream=response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }*/

//    @GetMapping("/export")
//    public void export(HttpServletResponse response)  throws IOException {
//        System.out.println(1);
//
//        //???????????????????????????????????????
//        List<Adminuser> users=adminUserService.list();
//
//        ExcelUtiles.exportExcel(users,"???????????????","????????????",Adminuser.class,"????????????.xls",response);
//    }


    /*
    @GetMapping("/export")
    public void export(HttpServletResponse response)  throws IOException {
        //https://www.dazhuanlan.com/2019/10/13/5da20d659f337/
        //https://www.erbaojun.cn/archives/109
        try {

            List<Map<String,Object>> sheets = new ArrayList<Map<String,Object>>();

            for(int i = 0;i<10;i++) {
                Map<String,Object> sheet = new HashMap<String,Object>();

                List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();//?????????????????????@Excel

                entity.add(new ExcelExportEntity("??????", "sex"));
                entity.add(new ExcelExportEntity("??????", "name"));

                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                Map<String,Object> h1 = new HashMap<String,Object>();
                h1.put("name", "name" + i);
                h1.put("sex", "sex" +  i);
                Map<String,Object> h2 = new HashMap<String,Object>();
                h2.put("name", "name" +i+i);
                h2.put("sex", "sex" +i+i);
                list.add(h1);
                list.add(h2);

                sheet.put(NormalExcelConstants.CLASS, ExcelExportEntity.class);
                sheet.put(NormalExcelConstants.DATA_LIST, list);
                sheet.put(NormalExcelConstants.PARAMS, new ExportParams(null, "sheet"+i));
                sheet.put(NormalExcelConstants.MAP_LIST, entity);
                sheets.add(sheet);
            }

            Workbook workbook = new HSSFWorkbook();

            for(Map<String,Object> map : sheets) {
                ExcelExportService server = new ExcelExportService();
                ExportParams param = (ExportParams) map.get("params");
                @SuppressWarnings("unchecked")
                List<ExcelExportEntity> entity = (List<ExcelExportEntity>) map.get("mapList");
                Collection<?> data = (Collection<?>) map.get("data");
                server.createSheetForMap(workbook, param, entity, data);
            }

//            FileOutputStream fos = new FileOutputStream("D:/ExcelExportForMap.tt.xls");
//            workbook.write(fos);
//            fos.close();
            response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode("????????????.xls","UTF-8"));
            ServletOutputStream outputStream=response.getOutputStream();
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
        } catch (java.io.FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    */

    @RequestMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        ArticleAO ao=new ArticleAO();
        ao.setPage(1);
        ao.setLimit(10);

        PagedResult<ArticleVO> pagedResult= articleService.findList(ao);
        Map<String, List<ArticleVO>> dic = pagedResult.getItems().stream().collect(Collectors.groupingBy(ArticleVO::getArticleTypeName));

        //???sheets?????????map????????????
        List<Map<String, Object>> sheets = new ArrayList<>();
        dic.forEach((k, v) -> {

            //????????????
            List<ExcelExportEntity> entity=CreatTbHeader();
            Map<String,Object> sheet = new HashMap<String,Object>();
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

            //????????????
            if(v.size()!=0)
            {
                v.forEach((item)->
                {
                    //Map<String, Object> mapItem=transBean2Map(item);
                    Map<String, Object> mapItem= JavaBean2Map.convertBean(item);
                    if(mapItem!=null)
                    {
                        list.add(mapItem);
                    }
                });
            }

            sheet.put(NormalExcelConstants.CLASS, ExcelExportEntity.class);
            sheet.put(NormalExcelConstants.DATA_LIST, list);
            sheet.put(NormalExcelConstants.PARAMS, new ExportParams(null, "sheet"+k));
            sheet.put(NormalExcelConstants.MAP_LIST, entity);
            sheets.add(sheet);

        });

        Workbook workbook = new HSSFWorkbook();

        for(Map<String,Object> map : sheets) {
            ExcelExportService server = new ExcelExportService();
            ExportParams param = (ExportParams) map.get("params");
            @SuppressWarnings("unchecked")
            List<ExcelExportEntity> entity = (List<ExcelExportEntity>) map.get("mapList");
            Collection<?> data = (Collection<?>) map.get("data");
            server.createSheetForMap(workbook, param, entity, data);
        }

        response.setHeader("content-disposition","attachment;fileName="+ URLEncoder.encode("????????????.xls","UTF-8"));
        ServletOutputStream outputStream=response.getOutputStream();
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
    }

    //????????????
    private  List<ExcelExportEntity> CreatTbHeader()
    {
        List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();

        entity.add(new ExcelExportEntity("ID", "Id"));
        entity.add(new ExcelExportEntity("ArticleTypeId", "ArticleTypeId"));
        entity.add(new ExcelExportEntity("ArticleTypeName", "ArticleTypeName"));
        entity.add(new ExcelExportEntity("UserId", "UserId"));
        entity.add(new ExcelExportEntity("TagIds", "TagIds"));
        entity.add(new ExcelExportEntity("Tags", "Tags"));
        entity.add(new ExcelExportEntity("Title", "Title"));
        entity.add(new ExcelExportEntity("LinkUrl", "LinkUrl"));
        entity.add(new ExcelExportEntity("Cover", "Cover"));
        entity.add(new ExcelExportEntity("Navigation", "Navigation"));
        entity.add(new ExcelExportEntity("Summary", "Summary"));
        entity.add(new ExcelExportEntity("Content", "Content"));
        entity.add(new ExcelExportEntity("IsUp", "IsUp"));
        entity.add(new ExcelExportEntity("IsRecommend", "IsRecommend"));
        entity.add(new ExcelExportEntity("IsHot", "IsHot"));
        entity.add(new ExcelExportEntity("OpenState", "OpenState"));
        entity.add(new ExcelExportEntity("ScanNum", "ScanNum"));
        entity.add(new ExcelExportEntity("LikeNum", "LikeNum"));
        entity.add(new ExcelExportEntity("CommentNum", "CommentNum"));
        entity.add(new ExcelExportEntity("ForwardNum", "ForwardNum"));
        entity.add(new ExcelExportEntity("IsSoftDelete", "IsSoftDelete"));
        entity.add(new ExcelExportEntity("Description", "Description"));
        entity.add(new ExcelExportEntity("CreateTime", "CreateTime"));
        entity.add(new ExcelExportEntity("ModifyTime", "ModifyTime"));
        entity.add(new ExcelExportEntity("TypeName", "TypeName"));
        return  entity;
    }
}
