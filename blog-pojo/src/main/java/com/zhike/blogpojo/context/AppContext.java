//package com.zhike.blogpojo.context;
//
//import com.alibaba.fastjson.JSON;
//import com.baomidou.mybatisplus.core.toolkit.StringUtils;
//import com.zhike.blogpojo.BO.BaseUser;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.UnsupportedEncodingException;
//import java.net.URLDecoder;
//
///**
// * Created with IntelliJ IDEA.
// * User: lenovo
// * Date: 2021/6/20
// * Time: 10:31
// * Description: No Description
// */
//@Component
//public class AppContext {
//
//    public  BaseUser  getCurrentUser() {
//
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = servletRequestAttributes.getRequest();
//        //String originValue = request.getHeader("gatewayuser");
//        // 测试用户
//        String originValue = "%7B%22Id%22%3A%22b46c6bba-79eb-46db-b521-1615e91c867c%22%2C%22DeptId%22%3A0%2C%22UserImId%22%3A2086186%2C%22DoctorId%22%3A%2200000000-0000-0000-0000-000000000000%22%2C%22UserType%22%3A0%2C%22Avatar%22%3A%22https%3A%5C%2F%5C%2Fcdn-ssl.meb.com%5C%2F2019%5C%2F4%5C%2F17%5C%2Fminfoxlogo512.png%22%2C%22IsManager%22%3Afalse%2C%22PositionId%22%3A0%2C%22IsNewUser%22%3Atrue%2C%22ThirdPlatforms%22%3A%5B%5D%2C%22HospitalId%22%3A%2200000000-0000-0000-0000-000000000000%22%2C%22Sex%22%3A2%2C%22IsDoctor%22%3Afalse%2C%22AppArray%22%3A%5B%5D%2C%22BeisenUserId%22%3A0%2C%22Status%22%3A0%2C%22MyInvitationCode%22%3Anull%2C%22NickName%22%3A%22%E5%8F%B6%E8%AF%97%E8%AF%AD%22%2C%22RealName%22%3A%22%E5%8F%B6%E8%AF%97%E8%AF%AD%22%2C%22UserName%22%3A%22yeshiyu%40meb.com%22%2C%22Mobile%22%3A%22yeshiyu%40meb.com%22%2C%22CreateTime%22%3Anull%2C%22UserId%22%3A0%7D";
//        if (StringUtils.isBlank(originValue)) {
//            return null;
//        }
//
//        String decodeValue = null;
//        try {
//            decodeValue = URLDecoder.decode(originValue, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        if (StringUtils.isBlank(decodeValue)) {
//            return null;
//        }
//
//        BaseUser user = JSON.parseObject(decodeValue, BaseUser.class);
//
//        String token = request.getHeader("usercachekey");
//        if (StringUtils.isNotBlank(token)) {
//            user.setUserCacheKey(token);
//            String[] segments = token.split(".");
//            user.setOldUserToken(segments.length > 1 ? segments[1] : "");
//        }
//
//        return user;
//    }
//}