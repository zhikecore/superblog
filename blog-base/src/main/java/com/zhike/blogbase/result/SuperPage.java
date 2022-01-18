package com.zhike.blogbase.result;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

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
 * SuperPage at 2022/1/16 21:20,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
public class SuperPage<T> extends Page<T> {

    private static final long serialVersionUID = 8545996863226528798L;

    /** 查询数据列表 */
    protected List<T> records = Collections.emptyList();

    /** 总数 */
    protected long total = 0;
    /** 每页显示条数，默认 10 */
    protected long size = 10;

    /** 当前页 */
    protected long current = 1;

    /** 排序字段信息 */
    @Getter
    @Setter
    protected List<OrderItem> orders = new ArrayList<>();

    /** 自动优化 COUNT SQL */
    protected boolean optimizeCountSql = true;
    /** 是否进行 count 查询 */
    protected boolean isSearchCount = true;
    /** 是否命中count缓存 */
    protected boolean hitCount = false;
    /** countId */
    @Getter @Setter protected String countId;
    /** countId */
    @Getter @Setter protected Long maxLimit;

    public SuperPage() {
        this(1, 10);
    }

    /**
     * 分页构造函数
     *
     * @param current 当前页
     * @param size 每页显示条数
     */
    public SuperPage(Integer current, Integer size) {
        this(current, size, 0);
    }

    public SuperPage(Integer current, Integer size, Integer total) {
        this(current, size, total, true);
    }

    public SuperPage(Integer current, Integer size, boolean isSearchCount) {
        this(current, size, 0, isSearchCount);
    }

    public SuperPage(Integer current, Integer size, Integer total, boolean isSearchCount) {
        if (current != null && 1 < current) {
            this.current = current;
        }
        if (size != null && 1 <= size) {
            this.size = size;
        }
        this.total = total;
        this.isSearchCount = isSearchCount;
    }

    @Override
    public List<T> getRecords() {
        return this.records;
    }

    @Override
    public SuperPage<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    @Override
    public long getTotal() {
        return this.total;
    }

    @Override
    public SuperPage<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    public SuperPage<T> setSize(long size) {
        this.size = size;
        return this;
    }

    @Override
    public long getCurrent() {
        return this.current;
    }

    @Override
    public SuperPage<T> setCurrent(long current) {
        this.current = current;
        return this;
    }

    @Override
    public String countId() {
        return getCountId();
    }

    @Override
    public Long maxLimit() {
        return getMaxLimit();
    }

    /**
     * 查找 order 中正序排序的字段数组
     *
     * @param filter 过滤器
     * @return 返回正序排列的字段数组
     */
    private String[] mapOrderToArray(Predicate<OrderItem> filter) {
        List<String> columns = new ArrayList<>(orders.size());
        orders.forEach(
                i -> {
                    if (filter.test(i)) {
                        columns.add(i.getColumn());
                    }
                });
        return columns.toArray(new String[0]);
    }

    /**
     * 移除符合条件的条件
     *
     * @param filter 条件判断
     */
    private void removeOrder(Predicate<OrderItem> filter) {
        for (int i = orders.size() - 1; i >= 0; i--) {
            if (filter.test(orders.get(i))) {
                orders.remove(i);
            }
        }
    }

    /**
     * 添加新的排序条件
     *
     * @param items 条件
     * @return 返回分页参数本身
     */
    @Override
    public SuperPage<T> addOrder(OrderItem... items) {
        orders.addAll(Arrays.asList(items));
        return this;
    }

    /**
     * 添加新的排序条件
     *
     * @param items 条件
     * @return 返回分页参数本身
     */
    @Override
    public SuperPage<T> addOrder(List<OrderItem> items) {
        orders.addAll(items);
        return this;
    }

    @Override
    public List<OrderItem> orders() {
        return getOrders();
    }

    @Override
    public boolean optimizeCountSql() {
        return optimizeCountSql;
    }

    @Override
    public boolean isOptimizeCountSql() {
        return optimizeCountSql();
    }

    @Override
    public boolean isSearchCount() {
        if (total < 0) {
            return false;
        }
        return isSearchCount;
    }

    @Override
    public SuperPage<T> setSearchCount(boolean isSearchCount) {
        this.isSearchCount = isSearchCount;
        return this;
    }

    @Override
    public SuperPage<T> setOptimizeCountSql(boolean optimizeCountSql) {
        this.optimizeCountSql = optimizeCountSql;
        return this;
    }
}
