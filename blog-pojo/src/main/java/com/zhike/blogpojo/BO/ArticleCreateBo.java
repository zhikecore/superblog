package com.zhike.blogpojo.BO;

import lombok.Data;

@Data
public class ArticleCreateBo {

    private Integer ArticleTypeId;

    /**
     * 标签,多个以逗号分隔
     */
    private String TagIds;

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
     * 摘要
     */
    private String Summary;

    private String MdContent;
    private String Content;

    private Integer UserId;
    private String  ArticleTypeName;
    private String Description;
}
