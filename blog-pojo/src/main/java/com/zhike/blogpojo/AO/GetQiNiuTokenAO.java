package com.zhike.blogpojo.AO;

import lombok.Data;

@Data
public class GetQiNiuTokenAO {
    /**
     * 上传文件类型
     */
    public String mimeType;
    /**
     * 上传文件上限大小。单位KB
     */
    public Integer maxLength;
    /**
     * BucketType 0=公共空间 1=私有空间
     */
    public Integer bucketType;
    /**
     * 过期时间(秒) 一小时
     */
    public Long expireTime = 3600L;
    /**
     * 是否缓存token
     */
    public Boolean useTokenCache;
    /**
     * 缓存Token Key名称(通常存储在redis)
     */
    public String cacheTokenKey;
}
