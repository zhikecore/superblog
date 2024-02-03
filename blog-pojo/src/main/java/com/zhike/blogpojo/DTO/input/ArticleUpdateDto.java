package com.zhike.blogpojo.DTO.input;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ArticleUpdateDto {
    @NotNull(message = "文章ID")
    private Integer Id;

    @NotNull(message = "文章类型不能为空")
    private Integer ArticleTypeId;

    /**
     * 标签,多个以逗号分隔
     */
    private String TagIds;

    /**
     * 标题
     */
    @NotNull(message = "文章标题不能为空")
    private String Title;

    /**
     * 链接
     */
    private String LinkUrl;

    /**
     * 封面
     */
    @NotNull(message = "文章封面不能为空")
    private String Cover;

    /**
     * 摘要
     */
    private String Summary;

    @NotNull(message = "文章内容不能为空")
    private String MdContent;

    private String Content;
    private String Description;
}
