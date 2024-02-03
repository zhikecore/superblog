package com.zhike.blogadmin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhike.blogbase.annotation.EnableAuth;
import com.zhike.blogbase.utils.BeanHelper;
import com.zhike.blogbase.utils.RedisUtils;
import com.zhike.blogbase.utils.ServletUtil;
import com.zhike.blogmanager.Article.ArticleManager;
import com.zhike.blogmanager.User.UserManager;
import com.zhike.blogpojo.AO.ArticleAO;
import com.zhike.blogpojo.BO.ArticleCreateBo;
import com.zhike.blogpojo.BO.ArticleUpdateBo;
import com.zhike.blogpojo.DO.Adminuser;
import com.zhike.blogpojo.DO.Article;
import com.zhike.blogpojo.DTO.input.ArticleCreateDto;
import com.zhike.blogpojo.DTO.input.ArticleUpdateDto;
import com.zhike.blogpojo.Query.LoginRequest;
import com.zhike.blogpojo.VO.ArticleVO;
import com.zhike.blogservice.AdminUserService;
import com.zhike.blogservice.ArticleService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Copyright (C) 2023  智客工坊(52interview.com)
 * The SpringBoot Super-blog Project.
 * All rights reserved.
 * <p>
 * > Github地址: https://github.com/zhikecore/superblog.git
 * > 教程地址: https://www.52interview.com/book/36
 * > 智客工坊社区：https://www.52interview.com/
 * <p>
 * 智客工坊(52interview.com) - 经验创造价值,分享成就未来。
 * <p>
 * ArticleController at 2023/09/30 09:18,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */

@Controller
@RequestMapping("/article")
@Slf4j
@EnableAuth
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Resource
    private AdminUserService adminUserService;

    @Resource
    private ArticleManager articleManager;

    @Resource private UserManager userManager;
    @Resource
    private RedisUtils redisUtils;

    //@RecordLog(funModule="default",funMethod="index")
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView Index(
            HttpServletRequest request,
            Article article,
            @RequestParam(value = "pageNum",defaultValue = "0") int pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {

        log.debug("debug:default index...");
        log.info("info: access to default index 为主页!");

        //封装值到AO
        ArticleAO ao = new ArticleAO();
        ao.setLimit(pageSize);
        ao.setPage(pageNum);

        //定义一个视图对象名字时index.html  前缀和后缀都有封装,只需要写名字
        ModelAndView modelAndView = new ModelAndView("article/index");
        IPage<ArticleVO> page=articleService.searchByPage(pageNum,pageSize,article.getTitle());

        //相当于setAttriute("pageInfo",pageInfo)
        modelAndView.addObject("page",page);
        modelAndView.addObject("article",article);;

        return modelAndView;

    }


//    @RecordLog(funModule= "/templates/article",funMethod="index")
    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    public ModelAndView detail(@PathVariable Integer id) {
        log.debug("debug:article detail...");
        HttpSession session =  ServletUtil.getRequest().getSession();
        String account = (String) session.getAttribute("aname");
        Adminuser loginUser=userManager.findByAccount(account);

        //定义一个视图对象名字时index.html  前缀和后缀都有封装,只需要写名字
        ModelAndView modelAndView = new ModelAndView("article/detail");
        ArticleVO articleVO=articleService.findById(id);
        //是否点赞
        //Boolean isLiked=isLiked(id,loginUser.getId());
        //articleVO.setIsLiked(isLiked);

        modelAndView.addObject("article",articleVO);
        return modelAndView;
    }

//    @RequestMapping("/create")
//    public String  create(
//            ArticleCreateDto dto,
//            HttpServletRequest request)
//    {
//        //对密码进行 md5 加密
//        String md5Password = DigestUtils.md5DigestAsHex(loginRequest.getPassword().getBytes());
//        boolean isOk=adminUserService.login(loginRequest.getAccount(),md5Password);
//        if(!isOk)
//        {
//            //return  "common/false";
//            //map.put("msg","用户名和密码不对!");
//            return "login";
//        }
//
//        HttpSession session =  request.getSession();
//        session.setAttribute("aname",loginRequest.getAccount());
//        //session.setAttribute("apassword",admin.getPassword());
//        //List<Adminuser> userlist =adminUserService.list();
//        //model.addAttribute("admin",aa);
//        //model.addAttribute("alist",userlist);
//        //重定向
//        //return "redirect:/default/index";
//        return "redirect:/";
//    }

    @GetMapping("/create")
    public String create(){
        return "article/create";
    }

    @PostMapping("/create")
    public String create(ArticleCreateDto dto){
        ArticleCreateBo bo = BeanHelper.convertBean(dto, ArticleCreateBo::new);
        HttpSession session =  ServletUtil.getRequest().getSession();
        String account = (String) session.getAttribute("aname");
        Adminuser loginUser=userManager.findByAccount(account);
        bo.setUserId(loginUser.getId());
        articleService.create(bo);
        //返回列表页的处理
        return "redirect:/article/index";
    }

    //进入到修改页
    @GetMapping("/modify/{id}")
    public String update(@PathVariable int id, Model model){
        Article article=articleService.getById(id);
        model.addAttribute("article",article);
        return "article/modify";
    }

    @PostMapping("/modify")
    @ApiOperation(value = "更新文章")
    public String update(ArticleUpdateDto dto){
        ArticleUpdateBo bo = BeanHelper.convertBean(dto, ArticleUpdateBo::new);
        HttpSession session =  ServletUtil.getRequest().getSession();
        String account = (String) session.getAttribute("aname");
        Adminuser loginUser=userManager.findByAccount(account);
        bo.setUserId(loginUser.getId());
        articleService.modify(bo);
        //返回列表页的处理
        return "redirect:/article/index";
    }
}
