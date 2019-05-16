package com.adriano.controledesac.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

@Configuration
@Profile({"default", "docker-compose"})
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport implements CachingConfigurer {

    @Value("${config.redis.hostname}")
    private String redisHostName;

    @Value("${config.redis.port}")
    private int redisPort;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory
                = new JedisConnectionFactory(new RedisStandaloneConfiguration(redisHostName, redisPort));
        return factory;
    }

    @Primary
    @Bean
    public RedisCacheManager cacheManager(final RedisConnectionFactory connectionFactory) {
        final RedisCacheWriter redisCacheWriter = RedisCacheWriter.lockingRedisCacheWriter(connectionFactory);
        final RedisCacheConfiguration cacheConfiguration
                = RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeKeysWith(fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(fromSerializer(new GenericJackson2JsonRedisSerializer()));
        return new RedisCacheManager(redisCacheWriter, cacheConfiguration);
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new RedisCacheErrorHandler();
    }

    public static class RedisCacheErrorHandler implements CacheErrorHandler {
        private final Logger logger = LoggerFactory.getLogger(RedisConfig.class);


        @Override
        public void handleCacheGetError(RuntimeException e, Cache cache, Object o) {
            logger.error("Redis instance DOWN", e);
        }

        @Override
        public void handleCachePutError(RuntimeException e, Cache cache, Object o, Object o1) {
            logger.error("Redis instance DOWN", e);
        }

        @Override
        public void handleCacheEvictError(RuntimeException e, Cache cache, Object o) {
            logger.error("Redis instance DOWN", e);
        }

        @Override
        public void handleCacheClearError(RuntimeException e, Cache cache) {
            logger.error("Redis instance DOWN", e);
        }
    }

}

