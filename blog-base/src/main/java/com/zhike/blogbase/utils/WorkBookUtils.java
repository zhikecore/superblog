package com.zhike.blogbase.utils;

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
 * WorkBookUtils at 2022/1/16 21:20,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
//import com.mos.bee.entity.dto.QrUserRecordsDownloadDTO;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.zhike.blogbase.exception.BizException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

public class WorkBookUtils {

    public static Workbook mutiSheet(List<Map<String, Object>> mapListList){
        return ExcelExportUtil.exportExcel(mapListList, ExcelType.XSSF);
    }

    public static Map<String, Object> createOneSheet(ExportParams exportParams, Class<?> clazz, List<?> data){
        Map<String, Object> map = new HashMap<>();
        map.put("title",exportParams);
        map.put("entity", clazz);
        map.put("data",data);
        return map;

        //Sheet设置
        //Map<String, Object> sheet = new HashMap<>();
        //sheet.put(NormalExcelConstants.CLASS, ExcelExportEntity.class);
        //sheet.put(NormalExcelConstants.DATA_LIST, data);
        //sheet.put(NormalExcelConstants, exportParams);
        //这边为了方便，sheet1和sheet2用同一个表头(实际使用中可自行调整)
        //sheet.put(NormalExcelConstants.MAP_LIST, entities);

        //return  sheet;
    }
    /*
     * 创建一个表格并填充内容
     * 返回map供工作簿使用
     * */
    public static Map<String, Object> createOneSheet(String sheetName,String title,Class<?> clazz,List<?> data){
        ExportParams exportParams = new ExportParams(title,sheetName, ExcelType.XSSF);
        return createOneSheet(exportParams,clazz,data);
    }

    /**
     * 将数据存储进response,调用接口就能下载文件
     */
    public static  void dataToResponse(HttpServletRequest request, HttpServletResponse response, File file, String fileName) throws IOException {
        OutputStream out = null;
        FileInputStream in = null;
        try {
            // 1.读取要下载的内容
            in = new FileInputStream(file);
            // 2. 告诉浏览器下载的方式以及一些设置
            // 解决文件名乱码问题，获取浏览器类型，转换对应文件名编码格式，IE要求文件名必须是utf-8, firefo要求是iso-8859-1编码
            String agent = request.getHeader("user-agent");
            if (agent.contains("FireFox")) {
                fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
            } else {
                fileName = URLEncoder.encode(fileName, "UTF-8");
            }
            // 设置下载文件的mineType，告诉浏览器下载文件类型
            String mineType = request.getServletContext().getMimeType(fileName);
            response.setContentType(mineType);
            // 设置一个响应头，无论是否被浏览器解析，都下载
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            // 将要下载的文件内容通过输出流写到浏览器
            out = response.getOutputStream();
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }

    /**
     * 设置文件路径 && 保证文件对象的正确打开
     */
    public static  File createDatafile(String fileDownloadTmpPath, String fileName) throws IOException {
        File dir = new File(fileDownloadTmpPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String resource = fileDownloadTmpPath + fileName;
        File myFile = new File(resource);//创建File对象，参数为String类型，表示目录名
        //判断文件是否存在，如不存在则调用createNewFile()创建新目录，否则跳至异常处理代码
        if (!myFile.exists()) {
            myFile.createNewFile();
        }
        return myFile;
    }

}
