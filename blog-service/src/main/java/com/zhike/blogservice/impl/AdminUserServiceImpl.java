package com.zhike.blogservice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhike.blogbase.exception.BizException;
import com.zhike.blogbase.utils.BeanHelper;
import com.zhike.blogbase.utils.StringUtil;
import com.zhike.blogdao.mapper.AdminuserMapper;
import com.zhike.blogpojo.AO.UserQueryAO;
import com.zhike.blogpojo.BO.CreateAdminUserRequestBo;
import com.zhike.blogpojo.DO.Adminuser;
import com.zhike.blogpojo.DO.ArticleType;
import com.zhike.blogpojo.VO.ArticleTypeVO;
import com.zhike.blogpojo.VO.UserVO;
import com.zhike.blogservice.AdminUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
 * AdminUserServiceImpl at 2022/1/16 21:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminuserMapper,Adminuser> implements AdminUserService {

    @Autowired
    private AdminuserMapper adminuserMapper;

    @Override
    public boolean login(String account,String passwordsalt)
    {
        QueryWrapper<Adminuser> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(Adminuser::getAccount, account)
                .eq(Adminuser::getPasswordSalt,passwordsalt);

        Adminuser adminuser=adminuserMapper.selectOne(queryWrapper);
        return adminuser!=null;
    }

    @Override
    public boolean register(CreateAdminUserRequestBo bo)
    {
        //1.参数验证
        if(!bo.getPassword().equals(bo.getRePassword()))
        {
            throw new BizException("两次输入的密码不一致,请检查!");
        }

        //2.检查用户是否存在
        Adminuser adminuserByPhone =
               adminuserMapper.selectOne(
                        new LambdaQueryWrapper<Adminuser>()
                                .eq(Adminuser::getPhone, bo.getPhone()));
        if(adminuserByPhone!=null)
        {
            throw new BizException("该手机号码已经被注册,请检查!");
        }

        Adminuser adminuserByUserName=
                adminuserMapper.selectOne(
                        new LambdaQueryWrapper<Adminuser>()
                                .eq(Adminuser::getAccount, bo.getAccount()));

        if(adminuserByUserName!=null)
        {
            throw new BizException("该账号已经被注册,请检查!");
        }

        //2.注册入库
        //对密码进行 md5 加密
        String md5Password = DigestUtils.md5DigestAsHex(bo.getPassword().getBytes());

        Adminuser adminuser=new Adminuser();
        adminuser.setAccount(bo.getAccount());
        adminuser.setPasswordSalt(md5Password);
        adminuser.setNickName(bo.getNickname());
        adminuser.setPhone(bo.getPhone());

        int i=adminuserMapper.insert(adminuser);

        if (i>0) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public IPage<UserVO> searchByPage(UserQueryAO ao)
    {
        //拼接查询条件
        LambdaQueryWrapper<Adminuser> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.select().like(StringUtil.isNotEmpty(ao.getAccount()),Adminuser::getAccount,ao.getAccount());

        Page<Adminuser> page = new Page<>(ao.getPage(),ao.getLimit(), true);

        //查询
        IPage<Adminuser> pagedResource=adminuserMapper.selectPage(page,queryWrapper);

        //封装VO
        List<UserVO> items =
                pagedResource.getRecords().stream()
                        .map(
                                item -> {
                                    UserVO vo=BeanHelper.convertBean(item,UserVO::new);
                                    return vo;
                                })
                        .collect(Collectors.toList());

        IPage<UserVO> pagedVo=new Page<UserVO>();
        BeanUtils.copyProperties(pagedResource, pagedVo);
        pagedVo.setRecords(items);

        return pagedVo;
    }

}
