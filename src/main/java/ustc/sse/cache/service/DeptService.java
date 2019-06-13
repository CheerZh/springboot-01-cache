package ustc.sse.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ustc.sse.cache.bean.Department;
import ustc.sse.cache.mapper.DepartmentMapper;

/**
 * @author ZHGQ
 * @project springboot-01-cache
 * @Package ustc.sse.cache.service
 * @date 2019/4/1-18:26
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */

@Service
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;


    /**
     * 缓存的数据能存入redis；
     * 第二次从缓存中查询就不能反序列化回来；
     * 存的是dept的json数据，CacheManager默认使用的是RedisTemplate<Object, Employee>
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "dept")
    public Department getDeptById(Integer id){
        System.out.println("查询部门"+id);
        Department department = departmentMapper.getDeptById(id);
        return department;
    }
}
