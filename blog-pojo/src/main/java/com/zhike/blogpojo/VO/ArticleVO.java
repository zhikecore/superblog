package com.zhike.blogpojo.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zhike.blogpojo.DO.Article;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

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
 * ArticleVO at 2022/1/16 21:04,code by JeffreyHu
 * You can contact author with zhikecore@foxmail.com.
 */
@Data
//public class ArticleVO extends Article implements Serializable {
public class ArticleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer Id;

    private Integer ArticleTypeId;

    /**
     * 文章类型名称
     */
    private String ArticleTypeName;

    /**
     * 所属用户ID
     */
    private Integer UserId;

    /**
     * 标签,多个以逗号分隔
     */
    private String TagIds;

    /**
     * 标签,多个以逗号分隔
     */
    private String Tags;

    /**
     * 标题
     */
    private String Title;

    /**
     * 链接
     */
    private String LinkUrl;

    /**
     * 封面
     */
    private String Cover;

    /**
     * 导航
     */
    private String Navigation;

    /**
     * 摘要
     */
    private String Summary;

    private String Content;

    /**
     * 是否置顶:0为否，1为是
     */
    private Boolean IsUp;

    /**
     * 是否编辑推荐:0为否，1为是
     */
    private Boolean IsRecommend;

    /**
     * 是否热点:0=否,1=是
     */
    private Boolean IsHot;

    /**
     * 开放状态：0=公开 1=会员可看 2=付费可看
     */
    private Integer OpenState;

    /**
     * 浏览次数
     */
    private Integer ScanNum;

    private Integer LikeNum;

    private Integer CommentNum;

    private Integer ForwardNum;

    /**
     * 是否软删除
     */
    private Boolean IsSoftDelete;

    private String Description;

    private LocalDateTime CreateTime;

    private LocalDateTime ModifyTime;

    //private  String TypeName;

}
