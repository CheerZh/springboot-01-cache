package ustc.sse.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import ustc.sse.cache.bean.Employee;
import ustc.sse.cache.mapper.EmployeeMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot01CacheApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;  //操作字符串的

    @Autowired
    RedisTemplate redisTemplate;  //k-v都是对象

    @Autowired
    RedisTemplate<Object, Employee> empRedisTemplate;

    @Test
    public void contextLoads() {

        Employee employee = employeeMapper.getEmpById(1);
        System.out.println(employee);
    }

    /**
     * redis常见的五大数据类型：
     * String (字符串)、List、Set、Hash、Zset(有序集合)
     * stringRedisTemplate.opsForValue() :字符串
     * stringRedisTemplate.opsForXxx()  :Xxx
     *
     *
     */
    @Test
    public void test01(){

//        stringRedisTemplate.opsForValue().append("msg","hello");
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);

        stringRedisTemplate.opsForList().leftPush("mylist","1");
        stringRedisTemplate.opsForList().leftPush("mylist","0");
        stringRedisTemplate.opsForList().leftPush("mylist","2");
        stringRedisTemplate.opsForList().leftPush("mylist","3");
        stringRedisTemplate.opsForList().leftPush("mylist","4");
        stringRedisTemplate.opsForList().leftPush("mylist","5");

    }

    //测试保存对象
    @Test
    public void test02(){

        Employee employee = employeeMapper.getEmpById(1);

        //保存对象默认使用序列化
        //redisTemplate.opsForValue().set("emp-01",employee);

        //1、将数据以json的方式保存
        // 1）、自己转
        // 2）、redisTemplate
        empRedisTemplate.opsForValue().set("emp-01",employee);


    }

}
