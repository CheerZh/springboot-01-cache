package ustc.sse.cache.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import ustc.sse.cache.bean.Employee;

import java.net.UnknownHostException;

/**
 * @author ZHGQ
 * @project springboot-01-cache
 * @Package ustc.sse.cache.config
 * @date 2019/4/1-17:22
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */

@Configuration
public class MyRedisConfig {

    @Bean
    public RedisTemplate<Object, Employee> empRedisTemplate(
            RedisConnectionFactory redisConnectionFactory
    ) throws UnknownHostException {

        RedisTemplate<Object,Employee> template =new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Employee> ser = new Jackson2JsonRedisSerializer<Employee>(Employee.class);
        template.setDefaultSerializer(ser);
        return template;
    }

    @Bean
    public RedisCacheManager employeeCacheManager(RedisTemplate<Object, Employee> empRedisTemplate){
        RedisCacheManager redisCacheManager= new RedisCacheManager(empRedisTemplate);
        //key多了一个前缀：CacheName作为前缀
        redisCacheManager.setUsePrefix(true);
        return redisCacheManager;
    }
}
