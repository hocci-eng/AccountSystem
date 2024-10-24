package com.example.account.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisExecProvider;
import redis.embedded.RedisServer;
import redis.embedded.util.Architecture;
import redis.embedded.util.OS;

import java.io.IOException;

@Configuration
public class LocalRedisConfig {

    @Value("${spring.data.redis.port}")
    private int redisPort;

    private RedisServer redisServer;

    @PostConstruct
    public void startRedis() throws IOException {
        // Redis 실행 파일 경로르 설정
        RedisExecProvider customRedisProvider = RedisExecProvider.defaultProvider()
                .override(OS.MAC_OS_X, Architecture.x86_64, "/Users/jay/redis/src/redis-server");
        redisServer = new RedisServer(customRedisProvider, redisPort);
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        if (redisServer != null) {
            redisServer.stop();
        }
    }
}
