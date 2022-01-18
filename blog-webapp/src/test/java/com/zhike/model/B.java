package com.zhike.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: lenovo
 * Date: 2021/6/18
 * Time: 23:11
 * Description: No Description
 */

@Data
public class B extends A implements Serializable{

    private float score;

    transient int age;

    public static String hobby = "football";

}