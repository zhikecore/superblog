package com.zhike.admin.controller;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 2021/4/12
 * Time: 21:07
 * Description: No Description
 */

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhike.blogpojo.AO.UserQueryAO;
import com.zhike.blogpojo.VO.DataTableVO;
import com.zhike.blogpojo.VO.UserVO;
import com.zhike.blogservice.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private AdminUserService adminUserService;

    @RequestMapping("/index")
    public  ModelAndView Index(
            String account,
            @RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {

        //封装值到AO
        UserQueryAO ao=new UserQueryAO();
        ao.setLimit(pageSize);
        ao.setPage(pageNum);

        //定义一个视图对象名字时index.html
        ModelAndView modelAndView = new ModelAndView("user/index");
        IPage<UserVO> page=adminUserService.searchByPage(ao);

        //相当于setAttriute("pageInfo",pageInfo)
        modelAndView.addObject("page",page);
        modelAndView.addObject("account",account);
        return modelAndView;

    }


    //查询
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    @ResponseBody
    public DataTableVO query(
            @RequestParam(required = false, name = "pageSize",defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, name = "startIndex",defaultValue = "0") Integer startIndex,
            @RequestParam(required = false, name = "pageIndex",defaultValue = "0") Integer pageIndex){

        //封装值到AO
        UserQueryAO ao=new UserQueryAO();
        ao.setLimit(pageSize);
        ao.setPage(pageIndex);

        //这里做查询操作
        IPage<UserVO> pageInfo=adminUserService.searchByPage(ao);

        DataTableVO vo=new DataTableVO();
        vo.setSEcho("1");
        vo.setITotalRecords(pageInfo.getTotal());
        vo.setAaData(pageInfo.getRecords());
        vo.setITotalDisplayRecords(pageInfo.getRecords().size());

        //返回
        return vo ;
    }

}
