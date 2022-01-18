package com.zhike.blogwebapi.Models;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 2021/7/31
 * Time: 20:34
 * Description: 冰箱类
 */
@SuppressWarnings("未使用")
@Deprecated
@Data
public class Fridge {

    /**
     * 存储
     */
    public  void store(Object obj)
    {
        System.out.println("把"+obj.toString()+"放进冰箱");
    }

    /**
     * 开门
     */
    public  void  openTheDoor()
    {
        System.out.println("开门");
    }

    /**
     * 关门
     */
    public  void  closeTheDoor()
    {
        System.out.println("关门");
    }
}
