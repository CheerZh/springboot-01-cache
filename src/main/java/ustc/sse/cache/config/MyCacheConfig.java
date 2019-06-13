package ustc.sse.cache.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author ZHGQ
 * @project springboot-01-cache
 * @Package ustc.sse.cache.config
 * @date 2019/3/31-21:27
 * @Copyright: (c) 2019 USTC. All rights reserved.
 *
 * @Description: 自定义key生成器
 */

@Configuration
public class MyCacheConfig {

    @Bean("myKeyGenerator")
    public KeyGenerator keyGenerator() {

        return new KeyGenerator() {

            @Override
            public Object generate(Object target, Method method, Object... params) {
                return method.getName() + "[" + Arrays.asList(params).toString() + "]";
            }
        };
    }
}
