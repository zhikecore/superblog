package com.zhike.blogwebapi.Models;

import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 2021/8/1
 * Time: 9:45
 * Description: 动物
 */
@SuppressWarnings("未使用")
@Deprecated
@Data
public class Animal {

    private Object head;//头
    private List<Object> feets;//四肢
    private Object body;//躯干
    private Object tail;//尾巴

    public void cry()
    {
        System.out.println("我被关进了冰箱!");
    }
}
