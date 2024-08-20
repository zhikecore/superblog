package com.zhike.blogadmin.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnExpression("${spring.redis.enabled}")
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.timeout}")
    private String timeout;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.database}")
    private int database;

    @Value("${spring.redis.connectionPoolSize}")
    private int connectionPoolSize;

    @Value("${spring.redis.connectionMinimumIdleSize}")
    private int connectionMinimumIdleSize;

    @Bean(name = "redissonClient")
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.setCodec(new StringCodec());
        SingleServerConfig singleServerConfig =
                config.useSingleServer()
                        .setAddress("redis://" + host + ":" + port)
                        .setDatabase(database)
                        .setConnectionPoolSize(connectionPoolSize)
                        .setConnectionMinimumIdleSize(connectionMinimumIdleSize)
                        .setTimeout(Integer.parseInt(timeout));
        if (StringUtils.isNotBlank(password)) {
            singleServerConfig.setPassword(password);
        }
        return Redisson.create(config);
    }
}
