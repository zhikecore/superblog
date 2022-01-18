package com.zhike.blogwebapi.Models;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 2021/8/1
 * Time: 8:32
 * Description: 狮子对象
 */
@SuppressWarnings("未使用")
@Deprecated
public class Lion  extends  Animal{

    // 覆盖（重写）方法
    public void cry() {
        System.out.println("吼~");
    }

    public String toString(){
        return "狮子";
    }
}
