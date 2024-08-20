package com.zhike.blogadmin.controller;

import cn.hutool.core.util.StrUtil;
import com.zhike.blogbase.exception.BizException;
import com.zhike.blogbase.utils.StringUtil;
import com.zhike.blogmanager.User.UserManager;
import com.zhike.blogpojo.DO.Adminuser;
import com.zhike.blogpojo.DTO.input.CreateAdminUserRequestDto;
import lombok.extern.java.Log;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

//@Controller
@RequestMapping("/test")
@RestController
@Log
public class TestController {

    @Resource
    private UserManager userManager;

    @Resource private RedissonClient redissonClient;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        System.out.println("ddd");
        Adminuser adminuser =userManager.findByAccount("luckyhu");
        return "hello world!";
    }

    /*
    * 模拟注册（重复提交或者并发请求）
    * redisson 分布式锁拦截
    * */
    @PostMapping("/register")
    public void register(@Validated  @RequestBody CreateAdminUserRequestDto dto) {
        String mutex = StrUtil.format("im:lock:user:{}", dto.getAccount());
        RLock lock = redissonClient.getLock(mutex);
        boolean successLock = lock.tryLock();
        if (!successLock) {
            // 获取分布式锁失败
            throw new BizException("该顾客已经在创建中了，account:"+dto.getAccount());
        }
        log.info("注册成功！account:"+dto.getAccount());
    }
}

