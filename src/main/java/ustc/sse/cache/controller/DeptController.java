package ustc.sse.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ustc.sse.cache.bean.Department;
import ustc.sse.cache.service.DeptService;

/**
 * @author ZHGQ
 * @project springboot-01-cache
 * @Package ustc.sse.cache.controller
 * @date 2019/4/1-18:38
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */

@RestController
public class DeptController {

    @Autowired
    DeptService deptService;

    @GetMapping("/dept/{id}")
    public Department getDept(@PathVariable("id") Integer id ){
        return deptService.getDeptById(id);
    }
}
