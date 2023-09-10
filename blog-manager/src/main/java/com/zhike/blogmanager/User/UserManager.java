package com.zhike.blogmanager.User;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhike.blogbase.exception.BizException;
import com.zhike.blogdao.mapper.AdminuserMapper;
import com.zhike.blogpojo.DO.Adminuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserManager {
    @Autowired
    private AdminuserMapper adminuserMapper;

    public Adminuser findById(Integer id)
    {
        QueryWrapper<Adminuser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Adminuser::getId, id);

        Adminuser adminuser =adminuserMapper.selectOne(queryWrapper);
        if (adminuser == null) {
            throw new BizException("用户不存在!");
        }

        return adminuser;
    }

    public Adminuser findByAccount(String account)
    {
        QueryWrapper<Adminuser> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Adminuser::getAccount,account);

        Adminuser adminuser =adminuserMapper.selectOne(queryWrapper);
        if (adminuser == null) {
            throw new BizException("用户不存在!");
        }

        return adminuser;
    }
}
