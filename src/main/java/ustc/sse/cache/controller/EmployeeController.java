package ustc.sse.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ustc.sse.cache.bean.Employee;
import ustc.sse.cache.service.EmployeeService;

/**
 * @author ZHGQ
 * @project springboot-01-cache
 * @Package ustc.sse.cache.controller
 * @date 2019/3/31-20:15
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */

@RestController
public class EmployeeController{

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
        Employee employee = employeeService.getEmp(id);
        return employee;
    }

    @GetMapping("/emp")
    public Employee update(Employee employee){

        Employee emp = employeeService.updateEmp(employee);
        return emp;
    }

    @GetMapping("delemp")
    public String deleteEmp(Integer id){

        employeeService.deleteEmp(id);
        return "success";
    }

    @GetMapping("/emp/lastname/{lastName}")
    public Employee getEmpByLastName(@PathVariable("lastName") String lastName){
        return employeeService.getEmpByLastName(lastName);
    }
}
