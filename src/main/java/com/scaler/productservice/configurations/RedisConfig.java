package com.scaler.productservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    
    //    @Bean(name="productRedisTemplate")
//    @Bean(name="productRedisTemplate")
    @Bean
    public RedisTemplate<String, Object> productRedisTemplate(RedisConnectionFactory redisConnectionFactory){
//        System.out.println(">>> Creating productRedisTemplate bean");
        RedisTemplate<String, Object> productRedisTemplate = new RedisTemplate<>();
        productRedisTemplate.setConnectionFactory(redisConnectionFactory);

//        productRedisTemplate.setKeySerializer(new StringRedisSerializer());
//        productRedisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        productRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
//        productRedisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer();

        productRedisTemplate.setKeySerializer(stringSerializer);
        productRedisTemplate.setHashKeySerializer(stringSerializer);
        productRedisTemplate.setValueSerializer(jsonSerializer);
        productRedisTemplate.setHashValueSerializer(jsonSerializer);
//
        productRedisTemplate.afterPropertiesSet();

        return productRedisTemplate;
    }
}
