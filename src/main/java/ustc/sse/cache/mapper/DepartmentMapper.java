package ustc.sse.cache.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ustc.sse.cache.bean.Department;

/**
 * @author ZHGQ
 * @project springboot-01-cache
 * @Package ustc.sse.cache.mapper
 * @date 2019/3/31-19:56
 * @Copyright: (c) 2019 USTC. All rights reserved.
 * @Description:
 */

@Mapper
public interface DepartmentMapper {

    @Select("SELECT * FROM department WHERE id=#{id}")
    public Department getDeptById(Integer id);
}
