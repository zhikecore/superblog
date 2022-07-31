package com.zhike.controller;

import com.zhike.blogbase.utils.BeanHelper;
import com.zhike.blogpojo.BO.CreateAdminUserRequestBo;
import com.zhike.blogpojo.DTO.input.CreateAdminUserRequestDto;
import com.zhike.blogpojo.Query.LoginRequest;
import com.zhike.blogservice.AdminUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

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
 * AccountController at 2022/1/16 18:50,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    //private final Logger log = LoggerFactory.getLogger(ArticleController.class);

    @Resource
    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping("/login")//主页
    public String index(){
        return "login";
    }

    @RequestMapping("/gologin")//登录获取用户信息存到session
    public String  gologin(
            LoginRequest loginRequest,
            HttpServletRequest request,
            //Models model
            Map<String,Object> map
    ){

        //对密码进行 md5 加密
        String md5Password = DigestUtils.md5DigestAsHex(loginRequest.getPassword().getBytes());
        boolean isOk=adminUserService.login(loginRequest.getAccount(),md5Password);
        if(!isOk)
        {
            //return  "common/false";
            //map.put("msg","用户名和密码不对!");
            return "login";
        }

        HttpSession session =  request.getSession();
        session.setAttribute("aname",loginRequest.getAccount());
        //session.setAttribute("apassword",admin.getPassword());
        //List<Adminuser> userlist =adminUserService.list();
        //model.addAttribute("admin",aa);
        //model.addAttribute("alist",userlist);
        //重定向
        //return "redirect:/default/index";
        return "redirect:/";
    }

    @RequestMapping("/register")//去注册页面
    public String register()
    {
        return  "register";
    }

    /**
     * 注册
     * */
    @PostMapping("/goregister")
    //@ResponseBody
    public String goregister(@Validated CreateAdminUserRequestDto dto){

        CreateAdminUserRequestBo bo =BeanHelper.convertBean(dto,CreateAdminUserRequestBo::new);
        Boolean result=adminUserService.register(bo);
        if(result)
        {
            return "redirect:/common/success";
            //return "/common/success";
        }else
        {
            return "redirect:/common/failure";
            //return "/common/failure";
        }
    }

    /* 注销登录
     * @param session
     * @return*/
    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();//使Session变成无效，及用户退出
        return "redirect:/account/login";
    }
}
