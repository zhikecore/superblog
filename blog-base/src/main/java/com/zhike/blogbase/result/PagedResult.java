package com.zhike.blogbase.result;

import lombok.Data;

import java.util.List;

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
 * PagedResult at 2022/1/16 21:20,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Data
public class PagedResult<T> {

    /**
     * 页码
     */
    private long page;

    /**
     * 每页记录数
     */
    private long limit;

    /**
     * 记录总数
     */
    private long total;

    /**
     * 总页数
     */
    private long totalPages;

    /**
     * 是否有更多数据
     */
    private boolean hasMore;

    /**
     * 每页数据列表
     */
    private List<T> items;
}
