package com.zhike.blogpojo.DTO.input;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ArticleUnLikeDto {
    @NotNull(message = "文章Id不能为空")
    private  Integer articleId;
}
