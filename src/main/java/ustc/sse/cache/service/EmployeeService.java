package ustc.sse.cache.service;

import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import ustc.sse.cache.bean.Employee;
import ustc.sse.cache.mapper.EmployeeMapper;

/**
 * @author ZHGQ
 * @project springboot-01-cache
 * @Package ustc.sse.cache.service
 * @date 2019/3/31-20:12
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */

@CacheConfig(cacheNames="emp")
//定义公共属性
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存；以后再要相同的数据，直接从缓存中获取，不用调用方法
     *
     * CacheManager管理多个Cache组件，真正的CRUD操作在Cache组件中，每一个缓存组件有自己唯一一个名字
     * 几个属性：
     *      cacheNames/value:指定缓存组件的名字，放到那些缓存中，数组的方式
     *      key:指定缓存数据时用的key，默认使用方法参数的值 ： 1 - 方法返回值
     *          编写SpELl： #id; 参数id的值  #a0 #p0 #root.args[0]
     *
     *      keyGenerator: key的生成器，可以自己指定，kye/keyGenerator 二选一
     *
     *      cacheManager：指定缓存管理器   或者用 cacheResolver：指定缓存解析器
     *
     *      condition：指定条件，符合才缓存
     *      unless： 除非；否定缓存
     *
     *      sync:是否使用异步模式  使用时不支持unless
     *
     *  原理：
     *    1、自动配置类
     *
     * @param id
     * @return
     */

    //key="#root.methodName+'['+#id+']'"

    @Cacheable(/*cacheNames={"emp"}*//*, keyGenerator = "myKeyGenerator", condition = "#a0>1 and #root.methodName eq 'aaa'",unless = "#result == null"*/)
    public Employee getEmp(Integer id){

        System.out.println("查询"+id+"号员工");
        Employee emp = employeeMapper.getEmpById(id);
        return emp;
    }

    /**
     * @CachePut : 既调用方法，又更新缓存数据
     * 修改了数据库的某个数据，同时更新缓存
     *
     * 调用时机：
     *  1、先调用目标方法
     *  2、缓存
     *
     *  默认的key与Cacheable不同，需改为一样
     */
    @CachePut(/*value = "emp",*/key="#employee.id")
    public Employee updateEmp(Employee employee){

        System.out.println("update emp");
        employeeMapper.updateEmp(employee);
        return employee;

    }

    /**
     * @CacheEvict ： 缓存清楚
     * 删除数据后，删除缓存
     * 通过key指定
     * allEntries = true :指定清除这个缓存的所有数据
     * beforeInvocation 指定执行时机：默认方法执行后，如果执行出错则不清空缓存
     */
    @CacheEvict(/*value = "emp",*/ key="#id")
    public void deleteEmp(Integer id){
        System.out.println("deleteEmp: "+id);
        //employeeMapper.deleteEmpById(id);
    }

    /**
     * 定义复杂缓存规则
     * @param lastName
     * @return
     */
    @Caching(
            cacheable = {
                    @Cacheable(/*value = "emp" ,*/ key = "#lastName")
            },
            put={
                    @CachePut(/*value = "emp",*/key= "#result.id"),
                    @CachePut(/*value = "emp",*/key= "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName){

        return employeeMapper.getEmpByLastName(lastName);

    }



}
