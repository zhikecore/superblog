package com.zhike.blogadmin.config;

import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UploadManagerConfig {

    @Bean
    public UploadManager init() {
        com.qiniu.storage.Configuration cfg =
                new com.qiniu.storage.Configuration(Region.autoRegion());
        return new UploadManager(cfg);
    }
}
