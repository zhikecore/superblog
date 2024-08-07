package com.zhike.blogpojo.DTO.input;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReqFileUploadTokenDto {
    @NotNull(message = "七牛云上传目录不能为空!")
    private String dir;

    @NotNull(message = "七牛云上传空间类型不能为空! 0=公共空间 1=私有空间")
    private Integer bucketType;
}
