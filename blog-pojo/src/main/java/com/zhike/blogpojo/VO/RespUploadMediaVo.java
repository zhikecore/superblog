package com.zhike.blogpojo.VO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RespUploadMediaVo {
    @ApiModelProperty(value = "七牛云fileKey")
    private String fileKey;

    @ApiModelProperty(value = "七牛云完整地址")
    private String fullUrl;
}
