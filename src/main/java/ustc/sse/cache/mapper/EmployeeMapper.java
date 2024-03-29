package ustc.sse.cache.mapper;

import org.apache.ibatis.annotations.*;
import ustc.sse.cache.bean.Employee;

/**
 * @author ZHGQ
 * @project springboot-01-cache
 * @Package ustc.sse.cache.mapper
 * @date 2019/3/31-19:56
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */

@Mapper
public interface EmployeeMapper {

    @Select ("SELECT * FROM employee WHERE id = #{id}")
    public Employee getEmpById(Integer id);

    @Update("UPDATE employee SET lastName = #{lastName}, email=#{email},gender=#{gender},d_id=#{dId} WHERE id=#{id}")
    public void updateEmp(Employee employee);

    @Delete("DELETE FROM employee WHERE id=#{id}")
    public void deleteEmpById(Integer id);

    @Insert("INSERT INTO employee(lastName,email,gender,d_id) VALUES(#{lastName},#{email},#{gender},#{dId})")
    public void insertEmployee(Employee employee);

    @Select("SELECT * FROM employee WHERE lastName = #{lastName}")
    public Employee getEmpByLastName(String lastName);
}
