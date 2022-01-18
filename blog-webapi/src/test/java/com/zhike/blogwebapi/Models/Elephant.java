package com.zhike.blogwebapi.Models;

import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 2021/7/31
 * Time: 20:35
 * Description: 大象类
 */
@SuppressWarnings("未使用")
@Deprecated
@Data
public class Elephant  extends  Animal{

    // 覆盖（重写）方法
    public void cry() {
        System.out.println("哞~");
    }

    public String toString(){
        return "大象";
    }
}
