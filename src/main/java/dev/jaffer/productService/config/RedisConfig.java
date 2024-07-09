package dev.jaffer.productService.config;

import dev.jaffer.productService.models.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


@Configuration
public class RedisConfig {

//    @Bean
//    LettuceConnectionFactory connectionFactory() {
//        return new LettuceConnectionFactory();
//    }
    @Bean
    public RedisTemplate<Long, Product> redisTemplate(RedisConnectionFactory connectionFactory) {

        RedisTemplate<Long, Product> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory); //
        return redisTemplate;
    }
}
