package com.zhike.blogbase.utils.QiNiuYun.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "upload.qiniu")
public class QiNiuProperties {
    /**
     * 域名
     */
    private String domain;

    /**
     * 从下面这个地址中获取accessKey和secretKey
     * https://portal.qiniu.com/user/key
     */
    private String accessKey;

    private String secretKey;

    /**
     * 存储空间名
     */
    private String publicBucket;

    private String privateBucket;

    private Long deleteSleepTime;

    private Boolean enableRecognition;
    private String privateUrl;
}

